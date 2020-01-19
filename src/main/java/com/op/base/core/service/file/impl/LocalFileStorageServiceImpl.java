package com.op.base.core.service.file.impl;

import com.op.base.core.service.file.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 本地文件存储服务，文件存储时会被压缩，当取文件时，文件会被解压。
 *
 * @author Du Ping
 * @date 2018-08-25
 */
@Service("fileStorageService")
@Slf4j
public class LocalFileStorageServiceImpl implements FileStorageService {

    private static final String FILE_DIR;
    private static final String TEMP_DIR;
    private static final int BUFFER_SIZE = 8192;

    static {
        FILE_DIR = System.getProperty("user.dir") + File.separator + "files" + File.separator;
        TEMP_DIR = System.getProperty("java.io.tmpdir") + File.separator + "files" + File.separator;
        try {
            FileUtils.forceMkdir(new File(FILE_DIR));
            FileUtils.forceMkdir(new File(TEMP_DIR));
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public String save(File file) throws IOException {

        String fileName = UUID.randomUUID().toString();
        String fullPath = FILE_DIR + fileName;

        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream source = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(fullPath);
             ZipOutputStream dest = new ZipOutputStream(fos)
        ) {

            byte[] buffer = new byte[BUFFER_SIZE];
            ZipEntry zipEntry = new ZipEntry(file.getName());
            dest.putNextEntry(zipEntry);
            int count;
            while ((count = source.read(buffer, 0, BUFFER_SIZE)) != -1) {
                dest.write(buffer, 0, count);
            }
            dest.flush();
            dest.finish();
            return fileName;
        }
    }

    @Override
    public File get(String fileKey) throws IOException {
        String fileName = FILE_DIR + fileKey;
        String destFile = TEMP_DIR + fileKey;
        try (FileInputStream fis = new FileInputStream(fileName);
             ZipInputStream zin = new ZipInputStream(new BufferedInputStream(fis))
        ) {
            ZipEntry entry;

            byte[] buffer = new byte[BUFFER_SIZE];
            while ((entry = zin.getNextEntry()) != null) {
                destFile = TEMP_DIR + entry.getName();
                try (FileOutputStream fos = new FileOutputStream(destFile);
                     BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER_SIZE)
                ) {
                    int count;
                    while ((count = zin.read(buffer, 0, BUFFER_SIZE)) != -1) {
                        dest.write(buffer, 0, count);
                    }
                    dest.flush();
                }
            }

            return new File(destFile);
        }
    }

    @Override
    public void delete(String fileKey) {
        FileUtils.deleteQuietly(new File(FILE_DIR + fileKey));
        FileUtils.deleteQuietly(new File(TEMP_DIR + fileKey));
    }
}

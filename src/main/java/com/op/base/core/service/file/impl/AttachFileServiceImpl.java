package com.op.base.core.service.file.impl;

import com.op.base.core.entity.AttachFile;
import com.op.base.core.mapper.AttachFileMapper;
import com.op.base.core.service.exception.NoSuchDataException;
import com.op.base.core.service.file.AttachFileService;
import com.op.base.core.service.file.FileStorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author impact
 */
@Service("attachFileService")
public class AttachFileServiceImpl implements AttachFileService {

    private final AttachFileMapper attachFileMapper;
    private final FileStorageService fileStorageService;

    @Autowired
    public AttachFileServiceImpl(AttachFileMapper attachFileMapper, FileStorageService fileStorageService) {
        this.attachFileMapper = attachFileMapper;
        this.fileStorageService = fileStorageService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AttachFile[] uploadFiles(String type, File[] files) throws IOException {
        AttachFile attachFile = null;
        AttachFile[] attachFiles = new AttachFile[files.length];
        int i = 0;
        for (File f : files) {
            attachFile = createAttachFile(type, f);
            attachFile.setFileName(fileStorageService.save(f));
            attachFile.setFilePath(f.getPath());
            attachFileMapper.insertSelective(attachFile);
            attachFile = attachFileMapper.selectByPrimaryKey(attachFile.getFileId());
            attachFiles[i++] = attachFile;
        }
        return attachFiles;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFileByFileId(String fileId) throws NoSuchDataException {
        AttachFile attachFile = attachFileMapper.selectByPrimaryKey(fileId);
        if (attachFile != null) {
            attachFileMapper.deleteByPrimaryKey(fileId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AttachFile updateFileNameByFileId(String originalName, String fileId) throws NoSuchDataException {
        AttachFile attachFile = attachFileMapper.selectByPrimaryKey(fileId);
        if (attachFile != null) {
            attachFileMapper.updateOriginalNameByPrimaryKey(fileId, originalName);
            attachFile.setFileOriginalName(originalName);
        }
        return attachFile;
    }

    @Override
    public AttachFile selectByFileId(String fileId) throws NoSuchDataException {
        return attachFileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public File getFile(String fileName) throws IOException {
        return fileStorageService.get(fileName);
    }

    @Override
    public PageInfo selectFilesByTypeAndPage(Integer page, Integer size, String fileType) {
        PageHelper.startPage(page, size);
        List<AttachFile> selectAll = attachFileMapper.selectFilesByFileType(fileType);
        return new PageInfo<>(selectAll);
    }

    @Override
    public List<AttachFile> selectFilesByType(String fileType) {
        return attachFileMapper.selectFilesByFileType(fileType);
    }

    private AttachFile createAttachFile(String type, File file) {
        AttachFile attachFile = new AttachFile();
        // 去除加上的时间戳，取得原文件名
        attachFile.setFileOriginalName(FilenameUtils.getName(file.getName()));
        attachFile.setFileSize(String.valueOf(file.length()));
        attachFile.setFileType(type);
        attachFile.setFileId(UUID.randomUUID().toString());
        return attachFile;
    }

}

package com.op.base.core.controller.file;

import com.op.base.core.entity.AttachFile;
import com.op.base.core.service.exception.NoSuchDataException;
import com.op.base.core.service.file.AttachFileService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author impact
 */
@RestController
@RequestMapping("/base/files")
@Validated
@Slf4j
public class AttachFileController {

    /**
     * linux
     * private static final String TEMP_DIR = "/usr/local/projects/file/";
     * win
     * D:\graduationSystem\
     * 本地：C:\Users\cl\Desktop\gpms1
     */
    private static final String TEMP_DIR = "C:\\Users\\cl\\Desktop\\gpms1";
    private static final int BUFFER_SIZE = 8192;

    private final AttachFileService attachFileService;

    @Autowired
    public AttachFileController(AttachFileService attachFileService) {
        this.attachFileService = attachFileService;
    }

    /**
     * 单文件上传
     * @param type 文件类型
     * @param file 文件
     * @return 文件信息
     * @throws IOException io异常
     */
    @PostMapping(value = "/single", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AttachFile uploadSingleFile(@RequestParam("type") String type,  @RequestPart("file") MultipartFile file) throws IOException {
        File savedFile = saveFile(file);
        try {
            return attachFileService.uploadFiles(type, new File[]{savedFile})[0];
        } finally {
            FileUtils.deleteQuietly(new File(FilenameUtils.getPathNoEndSeparator(savedFile.getCanonicalPath())));
        }
    }

    /**
     * 分页查询附件信息
     * @param page  页数
     * @param size  行数
     * @param fileType  附件类型
     * @return  分页附件信息数据
     */
    @GetMapping(value = "/allPage")
    public PageInfo getAllFileByTypeAndPage(@RequestParam(name = "page") Integer page,
                                            @RequestParam(name = "size") Integer size,
                                            @RequestParam(name = "fileType", required = false) String fileType) {
        return attachFileService.selectFilesByTypeAndPage(page, size, fileType);
    }

    /**
     * 文件下载
     * @param fileId    文件id
     * @param response  响应数据
     * @throws NoSuchDataException 无数据异常
     * @throws IOException         io异常
     */
    @GetMapping(value = "/download/{fileId}")
    public void downloadFile(@PathVariable(value = "fileId") String fileId, HttpServletResponse response) throws NoSuchDataException, IOException {
        AttachFile attachFile = attachFileService.selectByFileId(fileId);
        String fileName = attachFile.getFileName();
        File file = attachFileService.getFile(fileName);
        try {
            fileName = URLEncoder.encode(attachFile.getFileOriginalName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage());
        }
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             OutputStream os = response.getOutputStream()
        ) {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName*=UTF-8''" + fileName);
            byte[] buffer = new byte[BUFFER_SIZE];
            int i;
            while ((i = bis.read(buffer)) != -1) {
                os.write(buffer, 0, i);
            }
            os.flush();
        } finally {
            FileUtils.forceDelete(file);
        }
    }

    /**
     * 查询附件信息
     * @param fileType  附件类型
     * @return  附件信息数据
     */
    @GetMapping(value = "/all")
    public List<AttachFile> getAllFileByType(@RequestParam(name = "fileType", required = false) String fileType) {
        return attachFileService.selectFilesByType(fileType);
    }

    /**
     * 根据附件Id删除附件
     * @param fileId    附件Id
     * @throws NoSuchDataException  无数据异常
     */
    @DeleteMapping(value = "/{fileId}")
    public void delete(@PathVariable(value = "fileId") String fileId) throws NoSuchDataException {
        attachFileService.deleteFileByFileId(fileId);
    }

    /**
     * 根据附件ID修改附件名
     * @param fileOriginalName  附件名
     * @param fileId 附件ID
     * @return  附件信息
     * @throws NoSuchDataException  无数据异常
     */
    @PutMapping(value = "/{fileId}")
    public AttachFile updateFileOriginalNameById(@RequestParam(name = "fileOriginalName") String fileOriginalName,
                                                 @PathVariable(value = "fileId") String fileId) throws NoSuchDataException {
        return attachFileService.updateFileNameByFileId(fileOriginalName, fileId);
    }

    /**
     * 根据附件ID获取附件信息
     * @param fileId    附件ID
     * @return  附件信息
     * @throws NoSuchDataException  无数据异常
     */
    @GetMapping(value = "/{fileId}")
    public AttachFile getFileById(@PathVariable(value = "fileId") String fileId) throws NoSuchDataException {
        return attachFileService.selectByFileId(fileId);
    }

    /**
     * 保存文件工具方法
     * @param file 文件信息
     * @return 文件
     * @throws IOException io异常
     */
    private File saveFile(MultipartFile file) throws IOException {
        String fileName = FilenameUtils.getName(file.getOriginalFilename());
        String path = TEMP_DIR + getCurrentTimestamp() + File.separator;
        FileUtils.forceMkdir(new File(path));
        File newFile = new File(path + fileName);
        file.transferTo(newFile);
        return newFile;
    }

    private String getCurrentTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    }

}

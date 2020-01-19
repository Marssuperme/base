package com.op.base.core.service.file;

import com.op.base.core.entity.AttachFile;
import com.op.base.core.service.exception.NoSuchDataException;
import com.github.pagehelper.PageInfo;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author impact
 */
public interface AttachFileService {

    /**
     * 上传附件（可多个）
     * @param type 附件类型
     * @param files 附件
     * @return
     * @throws IOException
     */
    AttachFile[] uploadFiles(String type, File[] files) throws IOException;

    /**
     * 根据附件ID删除附件
     * @param fileId 附件ID
     */
    void deleteFileByFileId(String fileId) throws NoSuchDataException;

    /**
     * 根据附件ID更新附件名
     * @param fileOriginalName 原附件名
     * @param fileId 附件ID
     * @return
     */
    AttachFile updateFileNameByFileId(String fileOriginalName, String fileId) throws NoSuchDataException;

    /**
     * 根据附件ID获取附件信息
     * @param fileId 附件ID
     * @return 附件信息
     * @throws NoSuchDataException 数据不存在
     */
    AttachFile selectByFileId(String fileId) throws NoSuchDataException;

    /**
     * 获取附件
     * @param fileName 附件名
     * @return 附件文件
     * @throws IOException
     */
    File getFile(String fileName) throws IOException;

    /**
     * 根据附件类型分页获取附件
     * @param page 页数
     * @param size 行数
     * @param fileType 附件类型
     * @return 分页附件信息
     */
    PageInfo selectFilesByTypeAndPage(Integer page, Integer size, String fileType);

    /**
     * 根据附件类型获取附件信息
     * @param fileType 附件类型
     * @return  附件信息
     */
    List<AttachFile> selectFilesByType(String fileType);

}

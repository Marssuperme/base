package com.op.base.core.mapper;

import com.op.base.core.entity.AttachFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author impact
 */
@Mapper
@Component
public interface AttachFileMapper {

    int deleteByPrimaryKey(String fileId);

    int insert(AttachFile record);

    int insertSelective(AttachFile record);

    AttachFile selectByPrimaryKey(String fileId);

    int updateByPrimaryKeySelective(AttachFile record);

    int updateByPrimaryKey(AttachFile record);

    /**
     * 根据类型查找附件
     * @param fileType 文件类型
     * @return 附件信息
     */
    List<AttachFile> selectFilesByFileType(@Param(value = "fileType") String fileType);

    /**
     * 根据附件Id修改附件名
     * @param fileId    附件Id
     * @param fileOriginalName  附件名
     * @return 文件信息
     */
    int updateOriginalNameByPrimaryKey(String fileId, String fileOriginalName);
}
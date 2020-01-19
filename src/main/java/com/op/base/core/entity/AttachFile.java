package com.op.base.core.entity;

import lombok.*;

import java.util.Date;

/**
 * @author impact
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttachFile {

    private String fileId;

    private String fileOriginalName;

    private String fileName;

    private String filePath;

    private String fileSize;

    private String fileType;

    private String remark;

    private Date createTime;
}
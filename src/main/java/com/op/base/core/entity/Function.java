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
public class Function {
    private String functionId;

    private String functionCode;

    private String functionName;

    private String remark;

    private Date createTime;
}
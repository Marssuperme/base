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
public class Role {

    private String roleId;

    private String roleName;

    private String remark;

    private Date createTime;
}
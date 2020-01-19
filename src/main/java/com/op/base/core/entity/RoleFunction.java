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
public class RoleFunction {

    private String roleFunctionId;

    private String roleId;

    private String functionId;

    private String remark;

    private Date createTime;

    private Role role;

    private Function function;
}
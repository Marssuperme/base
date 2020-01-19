package com.op.base.core.entity;

import com.op.base.core.entity.common.RoleUser;
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
public class User extends RoleUser {

    private String userId;

    private String userAccount;

    private String userName;

    private String userPassword;

    private Integer userGender;

    private String userEmail;

    private String userPhone;

    private Integer state;

    private String remark;

    private Date createTime;
}
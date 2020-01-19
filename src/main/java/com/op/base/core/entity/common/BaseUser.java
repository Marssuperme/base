package com.op.base.core.entity.common;

import com.op.base.core.entity.common.RoleUser;
import lombok.*;

import java.util.Date;

/**
 * 登录通用用户
 * @author impact
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseUser extends RoleUser {

    private String userId;

    private String userAccount;

    private String userName;

    private String userPassword;

    private Integer userGender;

    private String userEmail;

    private String userPhone;

    private String userRemark;

    private Integer userState;

    private Date createTime;

}

package com.op.base.core.entity.authority.token;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 登录信息，用户登录时，需要填定登录账号，密码及身份，密码在传输时会加密。
 *
 * 用户输入账号后，前端需要调用/api/users/的相关接口取得用户所有的工作岗位，并选择一个岗位作为进入系统后的身份
 *
 * @author Du Ping
 * @date 2018-02-26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInfoDTO {

    @NotBlank(message = "{login.userAccount.null}")
    private String userAccount;

    @NotBlank(message = "{login.userPwd.null}")
    private String userPwd;

    private Integer roleType;

    @NotBlank(message = "{login.verificationCode.null}")
    private String verificationCode;

}

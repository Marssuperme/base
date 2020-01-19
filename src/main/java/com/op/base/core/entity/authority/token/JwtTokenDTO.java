package com.op.base.core.entity.authority.token;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 用户成功登录后，返回的数据类，包含用户信息，token等
 *
 * @author Du Ping
 * @date 2018-02-25
 */
@Getter
@Setter
@ToString
public class JwtTokenDTO {


    private String userId;


    private String userName;


    private Date issued;

    private Date expires;


    private String token;

}

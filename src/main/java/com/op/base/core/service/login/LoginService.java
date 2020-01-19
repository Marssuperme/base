package com.op.base.core.service.login;

import com.op.base.core.entity.common.BaseUser;

/**
 * @author impact
 */
public interface LoginService {

    /**
     * 根据角色、账号、密码获取用户信息
     * @param userAccount  账号
     * @param userPassword  密码
     * @param roleType 角色类型
     * @return baseUser 统一的登录返回实体信息
     */
    BaseUser selectByAccountAndPasswordAndRoleId(String userAccount, String userPassword, Integer roleType);

}

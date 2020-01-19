package com.op.base.core.service.login.impl;

import com.op.base.core.entity.common.BaseUser;
import com.op.base.core.entity.enums.RoleType;
import com.op.base.core.mapper.RoleMapper;
import com.op.base.core.mapper.UserMapper;
import com.op.base.core.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author impact
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private static final String TOKEN = "The default later added";
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public LoginServiceImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    /**
     * 根据角色、账号、密码获取用户信息
     * @param userAccount  账号
     * @param userPassword  密码
     * @param roleType 角色类型
     * @return  baseUser 统一的登录返回实体信息
     */
    @Override
    public BaseUser selectByAccountAndPasswordAndRoleId(String userAccount, String userPassword, Integer roleType) {
        BaseUser baseUser;
        String roleId;

        String roleName = RoleType.SUPERVISOR.getKey().equals(roleType) ? RoleType.SUPERVISOR.getValue() : RoleType.ADMIN.getValue();
        // 根据角色名称获取角色ID
        roleId = roleMapper.selectRoleIdByRoleName(roleName);
        // 用户信息
        baseUser = userMapper.userLogin(userAccount, userPassword, roleId);
        if (baseUser != null) {
            // 缺省设置token  供jwt使用
            baseUser.setToken(TOKEN);
            // 设置权限
            baseUser.setAuthority(userMapper.selectUserAuthority(baseUser.getUserId()));
        }

        return baseUser;
    }
}

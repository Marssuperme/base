package com.op.base.core.service.user.impl;

import com.op.base.core.entity.*;
import com.op.base.core.entity.enums.RoleType;
import com.op.base.core.mapper.RoleMapper;
import com.op.base.core.mapper.UserMapper;
import com.op.base.core.service.exception.NoSuchDataException;
import com.op.base.core.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author impact
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        String roleId = roleMapper.selectRoleIdByRoleName(RoleType.ADMIN.getValue());
        user.setRoleId(roleId);
        userMapper.insertSelective(user);
        user = userMapper.selectByPrimaryKey(user.getUserId());
        user.setRole(roleMapper.selectByPrimaryKey(roleId));
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserByUserId(String userId) throws NoSuchDataException {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user != null) {
            userMapper.deleteByPrimaryKey(userId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUserByUserId(User user, String userId) throws NoSuchDataException {
        User oldUser = userMapper.selectByPrimaryKey(userId);
        if (oldUser != null) {
            userMapper.updateByPrimaryKeySelective(user);
            if (user.getRoleId() != null) {
                user.setRole(roleMapper.selectByPrimaryKey(user.getRoleId()));
            }
        }
        return user;
    }

    @Override
    public boolean isUserAccountAvailability(String userAccount, String userId) {
        User user = userMapper.selectByUserAccount(userAccount);
        if (userId != null) {
            return user == null || userId.equals(user.getUserId());
        } else {
            return user == null;
        }
    }

    @Override
    public User selectByUserId(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getRoleId() != null) {
            user.setRole(roleMapper.selectByPrimaryKey(user.getRoleId()));
        }
        return user;
    }

    @Override
    public PageInfo selectAllUserByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<User> selectAll = userMapper.selectAllUser();
        return new PageInfo<>(selectAll);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }
}

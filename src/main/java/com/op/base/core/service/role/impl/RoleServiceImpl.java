package com.op.base.core.service.role.impl;

import com.op.base.core.entity.Role;
import com.op.base.core.mapper.RoleMapper;
import com.op.base.core.service.exception.NoSuchDataException;
import com.op.base.core.service.role.RoleService;
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
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper){
        this.roleMapper = roleMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role addRole(Role role) {
        role.setRoleId(UUID.randomUUID().toString());
        roleMapper.insertSelective(role);
        role = roleMapper.selectByPrimaryKey(role.getRoleId());
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleByRoleId(String roleId) throws NoSuchDataException {
        Role role = roleMapper.selectByPrimaryKey(roleId);
        if (role != null) {
            roleMapper.deleteByPrimaryKey(roleId);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role updateRoleByRoleId(Role role, String roleId) throws NoSuchDataException {
        Role oldRole = roleMapper.selectByPrimaryKey(roleId);
        if (oldRole != null) {
            roleMapper.updateByPrimaryKeySelective(role);
        }
        return role;
    }

    @Override
    public Role selectRoleByRoleId(String roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public PageInfo selectAllRoleByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Role> selectAll = roleMapper.selectAll();
        return new PageInfo<>(selectAll);
    }

    @Override
    public List<Role> selectAllRole() {
        return roleMapper.selectAll();
    }
}

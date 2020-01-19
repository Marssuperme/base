package com.op.base.core.service.role;

import com.op.base.core.entity.Role;
import com.op.base.core.service.exception.NoSuchDataException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {

    /**
     * 新增角色
     * @param role  角色信息
     * @return  角色信息
     */
    Role addRole(Role role);

    /**
     * 根据角色ID删除角色信息
     * @param roleId    角色ID
     * @throws NoSuchDataException  无数据异常
     */
    void deleteRoleByRoleId(String roleId) throws NoSuchDataException;

    /**
     * 根据角色ID更新角色信息
     * @param role  角色ID
     * @param roleId    角色信息
     * @return  角色信息
     * @throws NoSuchDataException 无数据异常
     */
    Role updateRoleByRoleId(Role role, String roleId) throws NoSuchDataException;

    /**
     * 根据角色ID获取角色信息
     * @param roleId    角色ID
     * @return  角色信息
     */
    Role selectRoleByRoleId(String roleId);

    /**
     * 分页获取所有角色
     * @param page 页数
     * @param size 行数
     * @return 分页数据
     */
    PageInfo selectAllRoleByPage(Integer page, Integer size);

    /**
     * 获取所有角色
     * @return  所有角色
     */
    List<Role> selectAllRole();
}

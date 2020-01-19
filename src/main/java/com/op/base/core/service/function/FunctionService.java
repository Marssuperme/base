package com.op.base.core.service.function;

import com.op.base.core.entity.Function;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface FunctionService {

    /**
     * 分配角色功能权限
     * @param roleId id
     * @param functions 功能数组
     */
    void grantFunction(String roleId, List<Function> functions);

    /**
     * 根据角色ID获取角色分配权限
     * @param roleId id
     * @return 权限列表
     */
    List<Function> selectFunctionByRoleId(String roleId);

    /**
     * 获取所有权限
     * @return 所有权限
     */
    List<Function> selectAllFunction();

    /**
     * 分页获取所有权限
     * @param page 页数
     * @param size 每页数
     * @return 分页数据
     */
    PageInfo selectAllFunctionByPage(Integer page, Integer size);

}

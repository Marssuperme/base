package com.op.base.core.mapper;

import com.op.base.core.entity.RoleFunction;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author impact
 */
@Mapper
@Component
public interface RoleFunctionMapper {

    int deleteByPrimaryKey(String roleFunctionId);

    int insert(RoleFunction record);

    int insertSelective(RoleFunction record);

    RoleFunction selectByPrimaryKey(String roleFunctionId);

    int updateByPrimaryKeySelective(RoleFunction record);

    int updateByPrimaryKey(RoleFunction record);

    /**
     * 根据角色ID删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);

    /**
     * 批量插入权限信息
     * @param list
     */
    void batchInsertRoleFunction(List<RoleFunction> list);
}
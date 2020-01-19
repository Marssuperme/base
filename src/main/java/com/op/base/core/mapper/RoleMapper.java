package com.op.base.core.mapper;

import com.op.base.core.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author impact
 */
@Mapper
@Component
public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询所有
     * @return
     */
    List<Role> selectAll();

    /**
     * 根据角色名称获取角色ID
     * @param roleName 角色名称
     * @return  角色ID
     */
    String selectRoleIdByRoleName(@Param(value = "roleName") String roleName);
}
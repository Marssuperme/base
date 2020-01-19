package com.op.base.core.mapper;

import com.op.base.core.entity.Function;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author impact
 */
@Mapper
@Component
public interface FunctionMapper {

    int deleteByPrimaryKey(String functionId);

    int insert(Function record);

    int insertSelective(Function record);

    Function selectByPrimaryKey(String functionId);

    int updateByPrimaryKeySelective(Function record);

    int updateByPrimaryKey(Function record);

    /**
     * 查询所有
     * @return
     */
    List<Function> selectAll();

    /**
     * 根据角色ID查询所有
     * @param roleId    角色ID
     * @return
     */
    List<Function> selectAllByRoleId(String roleId);
}
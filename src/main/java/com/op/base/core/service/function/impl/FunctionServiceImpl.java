package com.op.base.core.service.function.impl;

import com.op.base.core.entity.Function;
import com.op.base.core.entity.RoleFunction;
import com.op.base.core.mapper.FunctionMapper;
import com.op.base.core.mapper.RoleFunctionMapper;
import com.op.base.core.service.function.FunctionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author impact
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

    private final FunctionMapper functionMapper;
    private final RoleFunctionMapper roleFunctionMapper;

    @Autowired
    public FunctionServiceImpl(FunctionMapper functionMapper, RoleFunctionMapper roleFunctionMapper){
        this.functionMapper = functionMapper;
        this.roleFunctionMapper = roleFunctionMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void grantFunction(String roleId, List<Function> functions) {
        //  1.根据角色ID删除旧记录
        roleFunctionMapper.deleteByRoleId(roleId);
        //  2.遍历权限信息，生成角色权限信息
        List<RoleFunction> list = new ArrayList<>();
        for (Function function : functions) {
            list.add(new RoleFunction(UUID.randomUUID().toString(), roleId, function.getFunctionId(),null,null, null, null));
        }
        //  3.批量插入角色权限信息表
        roleFunctionMapper.batchInsertRoleFunction(list);
    }

    @Override
    public List<Function> selectFunctionByRoleId(String roleId) {
        return functionMapper.selectAllByRoleId(roleId);
    }

    @Override
    public List<Function> selectAllFunction() {
        return functionMapper.selectAll();
    }

    @Override
    public PageInfo selectAllFunctionByPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Function> selectAll = functionMapper.selectAll();
        return new PageInfo<>(selectAll);
    }
}

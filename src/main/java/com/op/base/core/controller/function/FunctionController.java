package com.op.base.core.controller.function;

import com.op.base.core.entity.Function;
import com.op.base.core.service.function.FunctionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author impact
 */
@RestController
@RequestMapping("/security/function")
@Validated
public class FunctionController {
    
    private final FunctionService functionService;

    @Autowired
    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    /**
     * 分配角色功能权限
     * @param functions 功能权限信息
     */
    @PostMapping(value = "/add/{roleId}")
    public void grant(@PathVariable(name = "roleId") String roleId,
                          @RequestBody List<Function> functions) {
        functionService.grantFunction(roleId, functions);
    }

    /**
     * 分页查询所有功能权限
     * @param page  页数
     * @param size  行数
     * @return  分页数据
     */
    @GetMapping(value = "/allPage")
    public PageInfo getAllByPage(@RequestParam(name = "page") Integer page,
                                 @RequestParam(name = "size") Integer size) {
        return functionService.selectAllFunctionByPage(page, size);
    }

    /**
     * 查询所有功能权限
     * @return  数据
     */
    @GetMapping(value = "/all")
    public List<Function> getAll(){
        return functionService.selectAllFunction();
    }

    /**
     * 根据角色ID获取权限信息
     * @param roleId    角色ID
     * @return  权限信息列表
     */
    @GetMapping(value = "/{roleId}")
    public List<Function> getRoleFunctionByRoleId(@PathVariable(name = "roleId") String roleId){
        return functionService.selectFunctionByRoleId(roleId);
    }

}

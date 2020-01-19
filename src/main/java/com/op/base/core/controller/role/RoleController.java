package com.op.base.core.controller.role;

import com.op.base.core.entity.Role;
import com.op.base.core.service.exception.NoSuchDataException;
import com.op.base.core.service.role.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author impact
 */
@RestController
@RequestMapping("/security/roles")
@Validated
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 创建角色
     * @param roleInfo 角色信息
     * @return 角色信息
     */
    @PostMapping(value = "/add")
    public Role create(@RequestBody @Validated Role roleInfo) {
        return roleService.addRole(roleInfo);
    }

    /**
     * 根据角色Id删除角色信息
     * @param roleId 角色id
     * @throws NoSuchDataException 无数据异常
     */
    @DeleteMapping(value = "/{roleId}")
    public void delete(@PathVariable(name = "roleId") String roleId) throws NoSuchDataException {
        roleService.deleteRoleByRoleId(roleId);
    }

    /**
     * 根据角色Id修改角色信息
     * @param roleId   角色id
     * @param roleInfo 角色信息
     * @return 角色信息
     */
    @PutMapping(value = "/{roleId}")
    public Role update(
            @PathVariable(value = "roleId") String roleId,
            @RequestBody @Validated Role roleInfo) throws NoSuchDataException {
        return roleService.updateRoleByRoleId(roleInfo, roleId);
    }

    /**
     * 根据角色ID获取角色信息
     * @param roleId    角色ID
     * @return  角色信息
     */
    @GetMapping(value = "/{roleId}")
    public Role getRoleById(@PathVariable(value = "roleId") String roleId){
        return roleService.selectRoleByRoleId(roleId);
    }

    /**
     * 分页查询所有角色
     * @param page  页数
     * @param size  行数
     * @return  分页数据
     */
    @GetMapping(value = "/allPage")
    public PageInfo getAllByPage(@RequestParam(name = "page") Integer page,
                                 @RequestParam(name = "size") Integer size) {
        return roleService.selectAllRoleByPage(page, size);
    }

    /**
     * 查询所有角色
     * @return  数据
     */
    @GetMapping(value = "/all")
    public List<Role> getAll(){
        return roleService.selectAllRole();
    }

}

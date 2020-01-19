package com.op.base.core.controller.user;

import com.op.base.core.entity.User;
import com.op.base.core.entity.common.SimpleDataDTO;
import com.op.base.core.service.exception.NoSuchDataException;
import com.op.base.core.service.user.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author impact
 */
@RestController
@RequestMapping("/security/userInfo")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 创建用户
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @PostMapping(value = "/add")
    public User add(@RequestBody @Validated User userInfo) {
        return userService.addUser(userInfo);
    }

    /**
     * 删除用户信息
     * @param userId 用户id
     * @throws NoSuchDataException 无数据异常
     */
    @DeleteMapping(value = "/{userId}/user")
    public void delete(@PathVariable(name = "userId") String userId) throws NoSuchDataException {
        userService.deleteUserByUserId(userId);
    }

    /**
     * 修改用户信息
     * @param userId   用户id
     * @param userInfo 用户信息
     * @return 用户信息
     */
    @PutMapping(value = "/{userId}/user")
    public User update(
            @PathVariable(value = "userId") String userId,
            @RequestBody @Validated User userInfo) throws NoSuchDataException {
        return userService.updateUserByUserId(userInfo, userId);
    }

    /**
     * 根据ID获取用户信息
     * @param userId   用户id
     * @return 用户信息
     */
    @GetMapping(value = "/{userId}")
    public User getUserInfo(@PathVariable(value = "userId") String userId) {
        return userService.selectByUserId(userId);
    }

    /**
     * 验证账号是否可用
     * @param userAccount 账号
     * @param userId      用户id
     * @return 是否可用
     */
    @GetMapping(value = "/availability")
    public SimpleDataDTO<Boolean> isUserAvailability(@RequestParam(name = "userAccount") String userAccount,
                                                       @RequestParam(name = "userId", required = false) String userId) {
        return new SimpleDataDTO<>(userService.isUserAccountAvailability(userAccount, userId));
    }

    /**
     * 分页查询所有用户
     * @param page  页数
     * @param size  行数
     * @return  分页数据
     */
    @GetMapping(value = "/allPage")
    public PageInfo getAllByPage(@RequestParam(name = "page") Integer page,
                                       @RequestParam(name = "size") Integer size) {
        return userService.selectAllUserByPage(page, size);
    }

    /**
     * 查询所有用户
     * @return  数据
     */
    @GetMapping(value = "/all")
    public List<User> getAll(){
        return userService.selectAllUser();
    }
}


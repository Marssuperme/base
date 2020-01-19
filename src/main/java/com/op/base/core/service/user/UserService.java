package com.op.base.core.service.user;

import com.op.base.core.entity.User;
import com.op.base.core.service.exception.NoSuchDataException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author impact
 */
public interface UserService {

    /**
     * 添加用户
     * @param user  用户信息
     * @return  用户信息
     */
    User addUser(User user);

    /**
     * 根据用户ID删除用户
     * @param userId    用户ID
     * @throws NoSuchDataException  无数据错误
     */
    void deleteUserByUserId(String userId) throws NoSuchDataException;

    /**
     * 根据用户ID更新用户信息
     * @param user    用户信息
     * @param userId    用户ID
     * @return  用户信息
     * @throws NoSuchDataException  无数据错误
     */
    User updateUserByUserId(User user, String userId) throws NoSuchDataException;

    /**
     * 根据用户ID获取用户信息
     * @param userId    用户ID
     * @return  用户信息
     */
    User selectByUserId(String userId);

    /**
     * 超级管理员添加普通管理员，判断新增账号是否可用
     * @param userAccount    用户账号
     * @param userId    用户Id
     * @return 查询结果
     */
    boolean isUserAccountAvailability(String userAccount, String userId);

    /**
     * 分页查询用户信息
     * @param page 页数
     * @param size 每页数量
     * @return 分页信息
     */
    PageInfo selectAllUserByPage(Integer page, Integer size);

    /**
     * 查询所有
     * @return 所有用户信息
     */
    List<User> selectAllUser();
}

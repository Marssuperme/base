package com.op.base.core.mapper;

import com.op.base.core.entity.common.BaseUser;
import com.op.base.core.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author impact
 */
@Mapper
@Component
public interface UserMapper {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 登录校验
     * @param userAccount   账号
     * @param userPassword  密码
     * @param roleId    角色ID
     * @return
     */
    BaseUser userLogin(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword, @Param("roleId") String roleId);

    /**
     * 根据用户账号查询
     * @param userAccount   用户账号
     * @return
     */
    User selectByUserAccount(String userAccount);

    /**
     * 查询所有
     * @return
     */
    List<User> selectAllUser();

    /**
     * 根据用户ID查询权限
     * @param userId    用户ID
     * @return
     */
    List<String> selectUserAuthority(String userId);
}
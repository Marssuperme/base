package com.op.base.core.entity.common;

import com.op.base.core.entity.Role;
import lombok.*;

import java.util.List;

/**
 * 用户角色，权限，token
 * @author impact
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleUser {

    private String token;

    private List<String> authority;

    private String roleId;

    private Role role;
}

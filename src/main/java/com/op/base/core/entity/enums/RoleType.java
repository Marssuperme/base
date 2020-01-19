package com.op.base.core.entity.enums;

/**
 * 枚举4种角色类型：超级管理员，普通管理员，教师，学生
 * @author impact
 */
public enum RoleType {

    /**
     * 超级管理员
     */
    SUPERVISOR(1, "超级管理员"),
    /**
     * 管理员
     */
    ADMIN(2, "管理员"),
    /**
     * 教师
     */
    TEACHER(3, "教师"),
    /**
     * 学生
     */
    STUDENT(4, "学生");

    Integer key;
    String value;

    RoleType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() { return key; }

    public String getValue() {
        return value;
    }
}

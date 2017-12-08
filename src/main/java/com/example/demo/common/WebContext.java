package com.example.demo.common;

/**
 * 上下文POJO,方便读取系统常用属性
 *
 * @author QuiFar
 * @version V1.0
 */
public class WebContext {
    /**
     * 当前登录用户ID
     */
    private Integer userId;
    /**
     * 当前登陆用户名
     */
    private String userName;
    /**
     * 当前登陆用户昵称
     */
    private String userNike;
    /**
     * 当前用户角色id
     */
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNike() {
        return userNike;
    }

    public void setUserNike(String userNike) {
        this.userNike = userNike;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "WebContext [userId=" + userId + ", userName=" + userName + ", userNike=" + userNike + ", roleId="
                + roleId + "]";
    }

}

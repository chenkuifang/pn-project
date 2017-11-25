package com.example.demo.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @Description: 用户实体类
 * @author QuiFar
 * @date 2017年11月11日 下午12:21:01
 * @version V1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = -7846848632214306244L;

	private Integer id; /* id */
	private String userName; /* 登陆用户名 */
	private String password; /* 登陆密码 */
	private Integer departmentId; /* 所属部门 */
	private Integer roleId; /* 用户所属角色id */
	private String userNike; /* 昵称 */
	private String email; /* 电子邮件 */
	private String mobile; /* 手机 */
	private String sex; /* 性别 */
	private Integer createId; /* 创建者 */
	private java.util.Date createTime; /* 创建时间 */
	private java.util.Date updateTime; /* 修改时间 */
	private Integer status; /* 状态 1 正常, 0 停用 */

	// 用户角色一对一映射关系
	private Role role;

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 返回 id
	 * 
	 * @return
	 */
	public Integer getId() {
		return this.id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 返回 登陆用户名
	 * 
	 * @return
	 */
	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 返回 登陆密码
	 * 
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 返回 所属部门
	 * 
	 * @return
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * 返回 用户所属角色id
	 * 
	 * @return
	 */
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setUserNike(String userNike) {
		this.userNike = userNike;
	}

	/**
	 * 返回 昵称
	 * 
	 * @return
	 */
	public String getUserNike() {
		return this.userNike;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 返回 电子邮件
	 * 
	 * @return
	 */
	public String getEmail() {
		return this.email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 返回 手机
	 * 
	 * @return
	 */
	public String getMobile() {
		return this.mobile;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 返回 性别
	 * 
	 * @return
	 */
	public String getSex() {
		return this.sex;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	/**
	 * 返回 创建者
	 * 
	 * @return
	 */
	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回 创建时间
	 * 
	 * @return
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 返回 修改时间
	 * 
	 * @return
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 返回 状态 1 正常, 0 停用
	 * 
	 * @return
	 */
	public Integer getStatus() {
		return this.status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("userName", this.userName)
				.append("password", this.password).append("departmentId", this.departmentId)
				.append("roleId", this.roleId).append("userNike", this.userNike).append("email", this.email)
				.append("mobile", this.mobile).append("sex", this.sex).append("createId", this.createId)
				.append("createTime", this.createTime).append("updateTime", this.updateTime)
				.append("status", this.status).append("role", this.role).toString();
	}
}

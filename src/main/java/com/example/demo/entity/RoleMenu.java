package com.example.demo.entity;

import java.io.Serializable;

/**
 * 角色菜单实体类
 *
 * @author QuiFar
 * @version V1.0
 */
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = -7183763306339320615L;
	
	private Integer id;
	private Integer roleId;
	private Integer menuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenu{" + "id=" + id + ", roleId=" + roleId + ", menuId=" + menuId + '}';
	}
}

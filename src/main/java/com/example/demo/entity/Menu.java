
package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author QuiFar
 * @version V1.0
 * @Description: 系统菜单实体类
 * @date 2017年11月24日 下午20:53:00
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -4716109663079532955L;

	private Integer menuId; /* menu_id */
	private Integer parentId; /* 父菜单ID，一级菜单为0 */
	private String name; /* 菜单名称 */
	private String url; /* 菜单URL */
	private Integer type; /* 类型 0：目录 1：菜单 2：按钮 */
	private String icon; /* 菜单图标 */
	private Integer orderNum; /* 排序 */
	private Date createTime; /* 创建时间 */
	private Date updateTime; /* 修改时间 */
	private Integer status; /* 状态 1：正常，0：暂停 */

	// 角色菜单设置用到，其他没用
	private String isChecked;/* 该菜单是否勾选 */

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * 返回 menu_id
	 *
	 * @return
	 */
	public Integer getMenuId() {
		return this.menuId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 返回 父菜单ID，一级菜单为0
	 *
	 * @return
	 */
	public Integer getParentId() {
		return this.parentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 菜单名称
	 *
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 返回 菜单URL
	 *
	 * @return
	 */
	public String getUrl() {
		return this.url;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 返回 类型 0：目录 1：菜单 2：按钮
	 *
	 * @return
	 */
	public Integer getType() {
		return this.type;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 返回 菜单图标
	 *
	 * @return
	 */
	public String getIcon() {
		return this.icon;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 返回 排序
	 *
	 * @return
	 */
	public Integer getOrderNum() {
		return this.orderNum;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("menuId", this.menuId).append("parentId", this.parentId)
				.append("name", this.name).append("url", this.url).append("type", this.type).append("icon", this.icon)
				.append("orderNum", this.orderNum).append("createTime", this.createTime)
				.append("updateTime", this.updateTime).toString();
	}
}
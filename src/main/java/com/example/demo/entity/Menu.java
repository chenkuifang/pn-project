
package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author QuiFar
 * @version V1.0
 * @Description: 系统菜单实体类
 * @date 2017年11月24日 下午20:53:00
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
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

}
package com.example.demo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 角色菜单实体类
 *
 * @author QuiFar
 * @version V1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = -7183763306339320615L;
	
	private Integer id;
	private Integer roleId;
	private Integer menuId;
}

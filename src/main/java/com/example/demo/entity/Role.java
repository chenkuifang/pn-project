package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户角色实体类
 * @author QuiFar
 * @date 2017年11月24日 下午10:53:00
 * @version V1.0
 */
@Getter
@Setter
@ToString
public class Role implements Serializable {

	private static final long serialVersionUID = -7938927706797685893L;
	
	/** 角色编码 */
	private Integer roleId;
	/** 角色名称 */
	private String roleName;
	/** 备注 */
	private String remark;
	/** 创建人 */
	private Integer createId;
	/** 创建时间 */
	private Date createTime;
	/** 最后更改 */
	private Date updateTime;
	/** 状态 0：停用，1：使用中 */
	private Integer status;
	private User user;
}

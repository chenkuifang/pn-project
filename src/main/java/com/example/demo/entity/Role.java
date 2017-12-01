package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户角色实体类
 * @author QuiFar
 * @date 2017年11月24日 下午10:53:00
 * @version V1.0
 */
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", remark=" + remark + ", createId=" + createId
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", status=" + status + "]";
	}

}

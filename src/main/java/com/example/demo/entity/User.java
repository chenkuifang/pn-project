package com.example.demo.entity;

import java.io.Serializable;

/**
 * @Description: 用户实体类
 * @author QuiFar
 * @date 2017年11月11日 下午12:21:01
 * @version V1.0
 */
public class User implements Serializable {
	private static final long serialVersionUID = 2027317201994678710L;

	private Integer id;
	private String userName;
	private String password;
	private String mobile;
	private String sex;
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", mobile=" + mobile + ", sex="
				+ sex + ", status=" + status + "]";
	}

}

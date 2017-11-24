package com.example.demo.common;

/**
 * @Description: 上下文POJO,方便读取系统需要的属性
 * @author QuiFar
 * @date 2017年11月23日 下午10:39:26
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

	@Override
	public String toString() {
		return "webContext [userId=" + userId + ", userName=" + userName + ", userNike=" + userNike + "]";
	}

}

package com.example.demo.common;

/**
 * @Description: 上下文POJO,方便读取系统需要的属性
 * @author QuiFar
 * @date 2017年11月23日 下午10:39:26
 * @version V1.0
 */
public class webContext {
	private Integer userId;
	private String userName;

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

	@Override
	public String toString() {
		return "webContext [userId=" + userId + ", userName=" + userName + "]";
	}

}

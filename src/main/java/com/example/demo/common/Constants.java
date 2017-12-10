package com.example.demo.common;

/**
 * 常量类
 *
 * @author QuiFar
 * @version V1.0
 */
public interface Constants {

	/**
	 * success description 成功
	 */
	String SUCCESS_DESCRIPTION = "成功";

	/**
	 * fail description 失败
	 */
	String FAIL_DESCRIPTION = "失败";

	/**
	 * return value fail description 返回值错误
	 */
	String RETURN_VALUE_FAIL_DESCRIPTION = "返回值错误";

	/**
	 * user name or password error 用户名或密码错误
	 */
	String USER_NAME_OR_PASSWORD_ERROR = "用户名或密码错误";

	/**
	 * password empty 密码为空
	 */
	String PASSWORD_EMPTY = "密码为空";

	/**
	 * 返回码 100 成功
	 */
	String SUCCESS_CODE = "100";
	/**
	 * 返回码 101 失败
	 */
	String FAIL_CODE = "101";

	/**
	 * 返回值错误 return value fail code
	 */
	String RETURN_VALUE_FAIL_CODE = "102";

	/**
	 * 用户信息存放session key 值
	 */
	String SESSION_USER = "sessionUser";

	/**
	 * 前端拦截放行访问路径，不需要进行session判断
	 */
	String NO_INTERCEPTOR_PATH = ".*/((login)|(loginOut)|(code)).*";

	/**
	 * 翻页参数拦截路径
	 */
	String PAGE_INTERCEPTOR_PATH = ".*/(listPage)*";
}

package com.example.demo.common;

/**
 * @Description: 常量类
 * @author QuiFar
 * @date 2017年11月19日 上午11:02:28
 * @version V1.0
 */
public interface Constants {

	String RESULT_SUCCESS_DESCRIPTION = "成功";
	String Result_FAIL_DESCRIPTION = "失败";

	/**
	 * 返回码 100 成功
	 */
	String RESULT_CODE_SUCCESS = "100";
	/**
	 * 返回码 101 失败
	 */
	String RESULT_CODE_FAIL = "101";

	/**
	 * 用户信息存放session key 值
	 */
	String SESSION_USER = "sessionUser";

	/**
	 * 前端拦截放行访问路径，不需要进行session判断
	 * 
	 */
	String NO_INTERCEPTOR_PATH = ".*/((login)|(loginOut)|(code)).*";

	/**
	 * 翻页参数拦截路径
	 * 
	 */
	String PAGE_INTERCEPTOR_PATH = ".*/(listPage)*";
}

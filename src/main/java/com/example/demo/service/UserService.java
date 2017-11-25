package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.User;

/**
 * @Description: 用户服务层接口
 * @author QuiFar
 * @date 2017年11月11日 下午12:42:58
 * @version V1.0
 */
public interface UserService {
	/**
	 * 根据主键删除单个对象
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	int remove(Integer id);

	/**
	 * 根据主键ID更新用户信息
	 * 
	 * @param user
	 *            需要更新的对象
	 * @return
	 */
	int update(User user);

	/**
	 * 新增信息
	 * 
	 * @param user
	 * @return
	 */
	int save(User user);

	/**
	 * 根据主键获取用户信息
	 * 
	 * @param id
	 *            编号
	 * @return
	 */
	User get(Integer id);

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	User getByUserName(String userName);

	/**
	 * 根据手机号码获取用户信息
	 * 
	 * @param mobile
	 *            手机号码
	 * @return
	 */
	User getByMobile(String mobile);

	/**
	 * 根据条件获取用户列表
	 * 
	 * @param params
	 * @return
	 */
	List<User> list(Map<String, Object> params);
}

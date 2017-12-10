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
	Integer remove(Integer id);
	
	/**
	 * 根据主键数据批量删除用户
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	Integer removeBatch(String[] ids);

	/**
	 * 根据主键ID更新用户信息
	 * 
	 * @param user
	 *            需要更新的对象
	 * @return
	 */
	Integer update(User user);

	/**
	 * 新增信息
	 * 
	 * @param user
	 * @return
	 */
	Integer add(User user);

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

	/**
	 * 根据条件获取用户列表(分页)
	 * 
	 * @param 1.过滤条件、2.分页参数必须包含page,limit
	 * @return
	 */
	List<User> listPage(Map<String, Object> params);

	/**
	 * 根据条件获取用户列表总行数(一般提供分页使用)
	 * 
	 * @param params
	 * @return
	 */
	Integer countPage(Map<String, Object> params);
}

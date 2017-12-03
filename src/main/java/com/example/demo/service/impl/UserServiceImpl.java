package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

/**
 * @Description: 用户服务层接口实现类
 * @author QuiFar
 * @date 2017年11月11日 下午12:44:00
 * @version V1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int remove(Integer id) {
		// 1.判断是否有删除的权限
		// 2.操作删除
		return userMapper.remove(id);
	}
	
	@Override
	public int removeBatch(String[] ids) {
		return userMapper.removeBatch(ids);
	}

	@Override
	public int update(User user) {
		return userMapper.update(user);
	}

	@Override
	public int add(User user) {
		return userMapper.add(user);
	}

	@Override
	public List<User> list(Map<String, Object> params) {
		return userMapper.list(params);
	}

	@Override
	public User get(Integer id) {
		return userMapper.get(id);
	}

	@Override
	public User getByUserName(String userName) {
		return userMapper.getByUserName(userName);
	}

	@Override
	public User getByMobile(String mobile) {
		return userMapper.getByMobile(mobile);
	}

	@Override
	public List<User> listPage(Map<String, Object> params) {
		return userMapper.list(params);
	}

	@Override
	public int countPage(Map<String, Object> params) {
		return userMapper.countPage(params);
	}

}

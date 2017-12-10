package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;

/**
 * 用户服务层接口实现类
 * 
 * @author QuiFar
 * @version V1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public Integer remove(Integer id) {
		// 1.判断是否有删除的权限
		// 2.操作删除
		return userMapper.remove(id);
	}

	@Override
	public Integer removeBatch(String[] ids) {
		return userMapper.removeBatch(ids);
	}

	@Override
	public Integer update(User user) {
		return userMapper.update(user);
	}

	@Override
	public Integer add(User user) {
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
		return userMapper.listPage(params);
	}

	@Override
	public Integer countPage(Map<String, Object> params) {
		return userMapper.countPage(params);
	}

}

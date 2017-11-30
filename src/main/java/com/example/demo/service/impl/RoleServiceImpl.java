package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;

/**
 * @Description: 系统菜单服务层接口实现类
 * @author QuiFar
 * @date 2017年11月25日 上午10:29:29
 * @version V1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public int remove(Integer id) {
		return roleMapper.remove(id);
	}

	@Override
	public int update(Role role) {
		return roleMapper.update(role);
	}

	@Override
	public int save(Role role) {
		return roleMapper.save(role);
	}

	@Override
	public Role get(Integer id) {
		return roleMapper.get(id);
	}

	@Override
	public List<Role> list(Map<String, Object> params) {
		return roleMapper.list(params);
	}

	@Override
	public List<Role> listPage(Map<String, Object> params) {
		return roleMapper.listPage(params);
	}

}

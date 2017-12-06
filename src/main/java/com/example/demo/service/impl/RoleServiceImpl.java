package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.RoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.service.RoleService;

/**
 * @author QuiFar
 * @version V1.0
 * @Description: 系统菜单服务层接口实现类
 * @date 2017年11月25日 上午10:29:29
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
	public int removeMenuByRoleId(Integer roleId) {
		return roleMapper.removeMenuByRoleId(roleId);
	}

	@Override
	public int removeBatch(String[] ids) {
		return roleMapper.removeBatch(ids);
	}

	@Override
	public int update(Role role) {
		return roleMapper.update(role);
	}

	@Override
	public int add(Role role) {
		return roleMapper.add(role);
	}

	@Override
	public int addMenuBatch(List<RoleMenu> roleMenus) {
		return roleMapper.addMenuBatch(roleMenus);
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

	@Override
	public int countPage(Map<String, Object> params) {
		return roleMapper.countPage(params);
	}

	@Override
	public List<Role> listByStatus(int status) {
		Map<String, Object> params = new HashMap<>();
		params.put("whereSql", "a.status = #{status}");
		params.put("status", status);
		return roleMapper.list(params);
	}

}

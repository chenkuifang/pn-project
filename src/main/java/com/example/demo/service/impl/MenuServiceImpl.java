package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Menu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.MenuService;

/**
 * 系统菜单服务层接口实现类
 * 
 * @author QuiFar
 * @version V1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Integer remove(Integer id) {
		return menuMapper.remove(id);
	}

	@Override
	public Integer update(Menu menu) {
		return menuMapper.update(menu);
	}

	@Override
	public Integer add(Menu menu) {
		return menuMapper.add(menu);
	}

	@Override
	public Menu get(Integer id) {
		return menuMapper.get(id);
	}

	@Override
	public List<Menu> list(Map<String, Object> params) {
		return menuMapper.list(params);
	}

	@Override
	public List<Menu> listByRoleId(Integer roleId) {
		return menuMapper.listByRoleId(roleId);
	}

	@Override
	public List<Menu> listCheckedByRoleId(Integer roleId) {
		return menuMapper.listCheckedByRoleId(roleId);
	}

	@Override
	public List<Menu> listPage(Map<String, Object> params) {
		return menuMapper.listPage(params);
	}

	@Override
	public Integer countPage(Map<String, Object> params) {
		return menuMapper.countPage(params);
	}

	@Override
	public Integer removeBatch(String[] ids) {
		return menuMapper.removeBatch(ids);
	}

	@Override
	public List<Menu> listByParentId(Integer parentId) {
		Map<String, Object> params = new HashMap<>();
		params.put("whereSql", "a.parent_id = #{parentId}");
		params.put("parentId", parentId);
		return menuMapper.list(params);
	}

	@Override
	public List<Menu> listByStatus(Integer status) {
		Map<String, Object> params = new HashMap<>();
		params.put("whereSql", "a.status = #{status}");
		params.put("status", status);
		return menuMapper.list(params);
	}

}

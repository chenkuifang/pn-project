package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Menu;
import com.example.demo.mapper.MenuMapper;
import com.example.demo.service.MenuService;

/**
 * @Description: 系统菜单服务层接口实现类
 * @author QuiFar
 * @date 2017年11月25日 上午10:29:29
 * @version V1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public int remove(Integer id) {
		return menuMapper.remove(id);
	}

	@Override
	public int update(Menu menu) {
		return menuMapper.update(menu);
	}

	@Override
	public int save(Menu menu) {
		return menuMapper.save(menu);
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

}

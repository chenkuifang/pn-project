package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Menu;

/**
 * @Description: 系统菜单服务层接口
 * @author QuiFar
 * @date 2017年11月25日 上午10:28:10
 * @version V1.0
 */
public interface MenuService {
	/**
	 * 根据主键删除菜单
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	int remove(Integer id);

	/**
	 * 根据主键ID更新菜单,Menu对象必须包括ID值
	 * 
	 * @param menu
	 *            需要更新的菜单
	 * @return
	 */
	int update(Menu menu);

	/**
	 * 新增菜单
	 * 
	 * @param menu
	 * @return
	 */
	int save(Menu menu);

	/**
	 * 根据主键获取菜单
	 * 
	 * @param id
	 * @return
	 */
	Menu get(Integer id);

	/**
	 * 根据条件获取菜单列表(非外链)
	 * 
	 * @param params
	 * @return
	 */
	List<Menu> list(Map<String, Object> params);

	/**
	 * 根据角色ID获取菜单列表 (获取该角色所属的菜单列表)
	 * 
	 * @param params
	 * @return
	 */
	List<Menu> listByRoleId(Integer roleId);
}

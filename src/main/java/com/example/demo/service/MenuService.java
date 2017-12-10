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
	Integer remove(Integer id);

	/**
	 * 根据主键数据批量删除菜单
	 * 
	 * @param ids
	 *            主键数组
	 * @return
	 */
	Integer removeBatch(String[] ids);

	/**
	 * 根据主键ID更新菜单,Menu对象必须包括ID值
	 * 
	 * @param menu
	 *            需要更新的菜单
	 * @return
	 */
	Integer update(Menu menu);

	/**
	 * 新增菜单
	 * 
	 * @param menu
	 * @return
	 */
	Integer add(Menu menu);

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
	 * @param whereSql
	 *            不为空 则根据该条件过滤
	 * @param orderSql
	 *            不为空 则根据该条件排序
	 * @return
	 */
	List<Menu> list(Map<String, Object> params);

	/**
	 * 根据状态值获取菜单列表
	 * 
	 * @param status
	 *            状态
	 * @return
	 */
	List<Menu> listByStatus(Integer status);

	/**
	 * 根据父级Id获取菜单列表
	 * 
	 * @param parentId
	 *            父级id
	 * @return
	 */
	List<Menu> listByParentId(Integer parentId);

	/**
	 * 根据条件获取菜单列表(分页用)
	 * 
	 * @param params
	 *            必须参数page、limit
	 * @return
	 */
	List<Menu> listPage(Map<String, Object> params);

	/**
	 * 根据条件获取菜单列表总行数(一般分页用)
	 * 
	 * @param params
	 * @return
	 */
	Integer countPage(Map<String, Object> params);

	/**
	 * 根据角色ID获取菜单列表 (获取该角色所属的菜单列表)
	 * 
	 * @param roleId
	 * @return
	 */
	List<Menu> listByRoleId(Integer roleId);

	/**
	 * 根据角色ID获取菜单所有列表 (获取该角色勾选的菜单列表) isChecked !=null 时表示勾选
	 * 
	 * @param roleId
	 * @return
	 */
	List<Menu> listCheckedByRoleId(Integer roleId);
}

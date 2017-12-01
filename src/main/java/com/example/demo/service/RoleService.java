package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Role;

/**
 * @Description: 角色设置服务层接口
 * @author QuiFar
 * @date 2017年11月25日 上午10:28:10
 * @version V1.0
 */
public interface RoleService {
	/**
	 * 根据主键删除角色
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	int remove(Integer id);

	/**
	 * 根据主键ID更新角色,Role对象必须包括ID值
	 * 
	 * @param role
	 *            需要更新的角色
	 * @return
	 */
	int update(Role role);

	/**
	 * 新增角色
	 * 
	 * @param role
	 * @return
	 */
	int add(Role role);

	/**
	 * 根据主键获取角色
	 * 
	 * @param id
	 * @return
	 */
	Role get(Integer id);

	/**
	 * 根据条件获取角色列表(非外链)
	 * 
	 * @param whereSql
	 *            不为空 则根据该条件过滤
	 * @param orderSql
	 *            不为空 则根据该条件排序
	 * @return
	 */
	List<Role> list(Map<String, Object> params);

	/**
	 * 根据条件获取角色列表(分页用)
	 * 
	 * @param params
	 *            必须参数page、limit
	 * @return
	 */
	List<Role> listPage(Map<String, Object> params);

}

package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Role;

/**
 * @Description: 角色映射接口
 * @author QuiFar
 * @date 2017年11月25日 上午9:57:33
 * @version V1.0
 */
@Mapper
public interface RoleMapper {
	/**
	 * 根据主键删除角色
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	int remove(Integer id);

	/**
	 * 根据主键数据批量删除角色
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	int removeBatch(String[] ids);

	/**
	 * 根据主键ID更新角色,role对象必须包括ID值
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
	 * 根据条件获取角色列表(分页)
	 * 
	 * @param 1.过滤条件、2.分页参数必须包含page,limit
	 * @return
	 */
	List<Role> listPage(Map<String, Object> params);

	/**
	 * 根据条件获取角色列表总行数(一般提供分页使用)
	 * 
	 * @param params
	 * @return
	 */
	int countPage(Map<String, Object> params);
}

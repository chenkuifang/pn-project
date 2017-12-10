package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleMenu;

/**
 * @author QuiFar
 * @version V1.0
 * @Description: 角色设置服务层接口
 * @date 2017年11月25日 上午10:28:10
 */
public interface RoleService {
    /**
     * 根据主键删除角色
     *
     * @param roleId 主键
     * @return
     */
	Integer remove(Integer roleId);

    /**
     * 根据角色ID删除菜单列表
     *
     * @param roleId
     * @return
     */
	Integer removeMenuByRoleId(Integer roleId);

    /**
     * 根据主键数据批量删除角色
     *
     * @param ids 主键数组
     * @return
     */
	Integer removeBatch(String[] ids);

    /**
     * 根据主键ID更新角色,Role对象必须包括ID值
     *
     * @param role 需要更新的角色
     * @return
     */
	Integer update(Role role);

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
	Integer add(Role role);

    /**
     * 批量新增角色菜单
     * 该方法会先根据角色ID清空原有的角色菜单数据
     *
     * @param roleMenus
     * @param roleId    角色ID
     * @return
     */
    void addMenuBatch(Integer roleId, List<RoleMenu> roleMenus);

    /**
     * 根据主键获取角色
     *
     * @param id
     * @return
     */
    Role get(Integer id);

    /**
     * 根据条件获取角色列表(非外链) whereSql 不为空 则根据该条件过滤 orderSql 不为空 则根据该条件排序
     *
     * @param params
     * @return
     */
    List<Role> list(Map<String, Object> params);

    /**
     * 根据状态获取角色列表
     *
     * @param status 状态值 0:暂停,1:使用中
     * @return
     */
    List<Role> listByStatus(Integer status);

    /**
     * 根据条件获取角色列表(分页用)
     *
     * @param params 必须参数page、limit
     * @return
     */
    List<Role> listPage(Map<String, Object> params);

    /**
     * 根据条件获取角色列表总行数(一般提供分页使用)
     *
     * @param params
     * @return
     */
    Integer countPage(Map<String, Object> params);

}

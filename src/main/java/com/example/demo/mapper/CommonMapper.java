package com.example.demo.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统公共方法映射接口
 * 
 * @author QuiFar
 * @version V1.0
 */
@Mapper
public interface CommonMapper {
	/**
	 * 获取指定数据表的某个字段的一行值，(按字段降序)
	 * 
	 * @param tableName
	 *            需要创建ID的数据库表名称,如"pn_user"
	 * @param field
	 *            数据表字段,如Id,user_id,menu_id等
	 * @return 因为会存在null 的情况下，所以返回类型用Map
	 */
	Map<String, Object> getTableNewId(Map<String, Object> params);

}

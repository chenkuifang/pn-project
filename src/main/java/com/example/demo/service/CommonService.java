package com.example.demo.service;

/**
 * 系统公共方法服务层接口
 * 
 * @author QuiFar
 * @version V1.0
 */
public interface CommonService {
	/**
	 * 获取一个数据表的新ID
	 * 
	 * @param tableName
	 *            需要创建ID的数据库表名称,如"pn_user"
	 * @param field
	 *            数据表字段,如Id,user_id,menu_id等
	 * @param initId
	 *            Id初始值,如10001,在该表为空的时候,该数据表的第一条数据的ID就是10001
	 * @return
	 */
	Integer getTableNewId(String tableName, String field, Integer initId);
}

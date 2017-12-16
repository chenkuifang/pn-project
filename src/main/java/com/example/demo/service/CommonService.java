package com.example.demo.service;

import com.example.demo.entity.Log;

import java.util.List;
import java.util.Map;

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
     * @param tableName 需要创建ID的数据库表名称,如"pn_user"
     * @param field     数据表字段,如Id,user_id,menu_id等
     * @param initId    Id初始值,如10001,在该表为空的时候,该数据表的第一条数据的ID就是10001
     * @return
     */
    int getTableNewId(String tableName, String field, Integer initId);

    /**
     * 系统操作日志添加
     *
     * @param clazz     操作类
     * @param method    调用方法
     * @param operation 操作方法类型
     * @param params    方法参数
     */
    void addLog(Class<?> clazz, String method, String operation, Object params);

    /**
     * 系统日志翻页列表
     *
     * @param params
     * @return
     */
    List<Log> listPageLog(Map<String, Object> params);

    /**
     * 根据条件获取日志列表总行数(一般分页用)
     *
     * @param params
     * @return
     */
    Integer countPageLog(Map<String, Object> params);
}

package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param tableName 需要创建ID的数据库表名称,如"pn_user"
     * @param field     数据表字段,如Id,user_id,menu_id等
     * @return 返回最前field 字段值
     */
    Object getTableNewId(@Param("tableName") String tableName, @Param("field") String field);

    /**
     * 系统操作日志添加
     *
     * @param log
     */
    void addLog(Log log);

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

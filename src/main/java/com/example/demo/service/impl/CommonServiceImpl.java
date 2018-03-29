package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.util.StringUtils;
import com.example.demo.common.util.WebContextUtils;
import com.example.demo.entity.Log;
import com.example.demo.mapper.CommonMapper;
import com.example.demo.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 系统公共方法服务层接口实现类
 *
 * @author QuiFar
 * @version V1.0
 */
@Service
public class CommonServiceImpl implements CommonService {

    private static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private CommonMapper commonMapper;

    public int getTableNewId(String tableName, String field, Integer initId) {
        Object result = commonMapper.getTableNewId(tableName, field);

        // 结果为空
        if (Objects.isNull(result)) {
            return initId;
        }

        if (!(result instanceof Integer)) {
            logger.debug("数据表：" + tableName + "的字段：" + field + "为非Integer类型");
        }

        // 原子操作
        int id = StringUtils.parseInteger(result);
        AtomicInteger atomicInteger = new AtomicInteger(id);
        return atomicInteger.incrementAndGet();
    }

    @Override
    public int getTableNewId(String tableName, String field) {
        return this.getTableNewId(tableName, field, 10001);
    }

    @Override
    public void addLog(Class<?> clazz, String method, String operation, Object params) {
        Log log = new Log();

        log.setUserId(WebContextUtils.getCurrentUserId());
        log.setUserName(WebContextUtils.getCurrentUserName());
        log.setIp(WebContextUtils.getRemoteAddr());
        log.setCreateTime(new Date());
        log.setMethod(new StringBuilder(clazz.getSimpleName()).append(".").append(method).toString());
        log.setOperation(operation);
        log.setParams(JSONObject.toJSONString(params));

        commonMapper.addLog(log);
    }

    @Override
    public List<Log> listPageLog(Map<String, Object> params) {
        return commonMapper.listPageLog(params);
    }

    @Override
    public Integer countPageLog(Map<String, Object> params) {
        return commonMapper.countPageLog(params);
    }
}

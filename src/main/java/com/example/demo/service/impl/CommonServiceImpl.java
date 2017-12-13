package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.util.StringUtils;
import com.example.demo.mapper.CommonMapper;
import com.example.demo.service.CommonService;

/**
 * 系统公共方法服务层接口实现类
 * 
 * @author QuiFar
 * @version V1.0
 */
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonMapper commonMapper;

	public Integer getTableNewId(String tableName, String field, Integer initId) {
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("field", field);
		Map<String, Object> result = commonMapper.getTableNewId(params);

		// 结果集包含null
		if (Objects.isNull(result) || !result.containsKey(field)) {
			return initId;
		}
		
		// 原子操作
		int id = StringUtils.parseInteger(result.get(field));
		AtomicInteger atomicInteger = new AtomicInteger(id);
		return atomicInteger.incrementAndGet();

	}
}

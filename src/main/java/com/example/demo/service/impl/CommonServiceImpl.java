package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.CommonMapper;
import com.example.demo.service.CommonService;

/**
 * @Description: 系统公共方法服务层接口实现类
 * @author QuiFar
 * @date 2017年12月1日 上午 12:28:10
 * @version V1.0
 */
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private CommonMapper commonMapper;

	public int getTableNewId(String tableName, String field, Integer initId) {
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableName);
		params.put("field", field);
		int result = commonMapper.getTableNewId(params);

		if (result == 0) {
			return initId;
		} else {
			return result + 1;
		}
	}
}

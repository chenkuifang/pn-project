package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;

/**
 * @Description: 菜单管理控制器
 * @author QuiFar
 * @date 2017年11月26日 上午10:32:27
 * @version V1.0
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(path = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list() {
		return "/menu/list";
	}
	
	@GetMapping("/listData")
	@ResponseBody
	public Map<String, Object> listData(HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderSql", "order_num");
		List<Menu> list = menuService.list(params);
		
		resultMap.put("code", 0);
		resultMap.put("msg", "ok");
		resultMap.put("count", list.size());
		resultMap.put("data", list);
		return resultMap;
	}

	@GetMapping("/table")
	public String table() {
		return "/table";
	}

}

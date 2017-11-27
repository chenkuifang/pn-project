package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public void list(HttpSession session, Model model) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("orderSql", "order_num");
		List<Menu> list = menuService.list(params);
		model.addAttribute("dataList", list);
	}
}

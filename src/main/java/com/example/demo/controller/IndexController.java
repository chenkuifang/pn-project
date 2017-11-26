package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;
import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;

/**
 * @Description: 首页控制类
 * @author QuiFar
 * @date 2017年11月18日 下午4:21:41
 * @version V1.0
 */

@Controller
public class IndexController {

	@Autowired
	private MenuService menuService;

	/**
	 * 访问首页
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String index(HttpSession session, Model model) {
		WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
		model.addAttribute("webContext", webContext);

		// 左边菜单信息
		return "index";
	}

	@GetMapping("listMenu")
	@ResponseBody
	public List<Menu> listMenu(HttpSession session) {
		// 当前登录信息
		WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
		int roleId = webContext.getRoleId();
		System.err.println(menuService.listByRoleId(roleId).toString());
		// 获取该角色的系统菜单列表
		return menuService.listByRoleId(roleId);
	}

}

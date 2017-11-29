package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.common.Constants;
import com.example.demo.common.WebContext;

/**
 * @Description: 首页控制类
 * @author QuiFar
 * @date 2017年11月18日 下午4:21:41
 * @version V1.0
 */

@Controller
public class IndexController {

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
		return "index";
	}
}

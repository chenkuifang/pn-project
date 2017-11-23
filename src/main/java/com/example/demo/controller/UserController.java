package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/**
 * @Description: 用户控制类
 * @author QuiFar
 * @date 2017年11月11日 下午1:38:51
 * @version V1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 列表展示
	 * 
	 * @return
	 */
	@RequestMapping(path = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView list(ModelAndView mView) {
		Map<String, Object> params = new HashMap<>();
		List<User> list = userService.list(params);
		mView.addObject("data", list);
		mView.setViewName("listUser");
		return mView;
	}

	/**
	 * 新增初始化view
	 * 
	 * @param model
	 *            需要返回的数据模型对象
	 * @return 表示返回的ViewName,该值用视图解析器处理,最终还是封装成ModelAndView对象
	 */
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("data", new User());
		return "editUser";
	}

	/**
	 * 编辑初始化View
	 * 
	 * @param id
	 *            编码
	 * @param model
	 *            需要返回的模型对象
	 * @return
	 */
	@GetMapping("/edit")
	public String edit(@PathParam("id") Integer id, Model model) {
		User user = userService.get(id);
		model.addAttribute("data", user);
		return "editUser";
	}

	/**
	 * 保存
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/save")
	public String save(User user) {
		// 新增
		if (user.getId() == null)
			userService.save(user);
		// 更新
		else
			userService.update(user);
		return "redirect:/user/list";
	}

	/**
	 * 根据ID移除单个对象
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/remove")
	public String remove(@PathParam("id") Integer id) {
		userService.remove(id);
		return "redirect:/user/list";
	}

	/**
	 * 根据id数组来批量移除
	 * 
	 * @param ids
	 *            编码数组
	 * @return
	 */
	@PostMapping(path = "/removeBatch", consumes = "application/json")
	public String removeBatch(@RequestBody String[] ids) {
		// userService.remove(id);
		return "redirect:/user/list";
	}

}

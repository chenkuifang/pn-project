package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.ResultBean;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

/**
 * @Description: 角色设置控制器
 * @author QuiFar
 * @date 2017年11月26日 上午21:32:27
 * @version V1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list() {
		return "/role/list";
	}

	/**
	 * 根据条件获取角色列表
	 * 
	 * @param params
	 *            页面请求参数
	 * @return
	 */
	@GetMapping("/listPage")
	@ResponseBody
	public ResultBean listPage(@RequestParam Map<String, Object> params) {
		double page = Double.parseDouble(params.get("page").toString());
		double limit = Double.parseDouble(params.get("limit").toString());
		params.put("offset", (int) ((page - 1) * limit));
		List<Role> list = roleService.listPage(params);

		// 返回数据
		ResultBean resultBean = ResultBean.getInstance();
		resultBean.setCount(list.size());
		resultBean.setData(list);
		return resultBean;
	}

	@GetMapping("/add")
	public String add() {
		return "/role/add";
	}
}

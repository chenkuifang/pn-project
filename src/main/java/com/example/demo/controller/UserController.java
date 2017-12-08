package com.example.demo.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.common.Constants;
import com.example.demo.common.R;
import com.example.demo.common.ResultBean;
import com.example.demo.common.WebContext;
import com.example.demo.common.util.MDUtils;
import com.example.demo.common.util.WebContextUtils;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.CommonService;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleService;
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
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private CommonService commonService;

	/**
	 * 列表展示
	 * 
	 * @return 视图名称
	 */
	@RequestMapping(path = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list() {
		return "/user/list";
	}

	/**
	 * 根据条件获取列表
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
		List<User> list = userService.listPage(params);

		// 返回数据
		ResultBean resultBean = ResultBean.getInstance();
		resultBean.setCount(userService.countPage(params));
		resultBean.setData(list);
		return resultBean;
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
		// 初始值
		User user = new User();
		user.setStatus(1);
		user.setSex(1);
		// 角色列表
		List<Role> roles = roleService.listByStatus(1);

		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("action", "add");
		return "/user/edit";
	}

	/**
	 * 编辑初始化View
	 * 
	 * @param id
	 *            编码
	 * @param model
	 *            需要返回的模型对象
	 * @return 视图名称
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		User user = userService.get(id);
		// 角色列表
		List<Role> roles = roleService.listByStatus(1);

		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		model.addAttribute("action", "edit");
		return "/user/edit";
	}

	/**
	 * 保存
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/save")
	@ResponseBody
	public R save(HttpSession session, User user) throws Exception {
		R r = R.getInstance();

		int flag = 0;
		// 新增
		if (user.getId() == null) {
			int id = commonService.getTableNewId("pn_user", "id", 10001);
			user.setId(id);
			// 暂时没有部门信息
			user.setDepartmentId(10001);
			// 初始化密码
			user.setPassword(MDUtils.encodeMD5("123456"));
			user.setCreateId(WebContextUtils.getCurrentUserId(session));
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			flag = userService.add(user);
		} else {
			// role.setUpdateTime(new Date());
			flag = userService.update(user);
		}

		// 结果返回
		if (flag > 0) {
			r.setCode(Constants.SUCCESS_CODE);
			r.setMsg(Constants.SUCCESS_DESCRIPTION);
		} else {
			r.setCode(Constants.FAIL_CODE);
			r.setMsg(Constants.FAIL_DESCRIPTION);
		}
		return r;
	}

	/**
	 * 根据ID移除单个对象
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/remove")
	@ResponseBody
	public R remove(@RequestParam("id") Integer id) {
		R r = R.getInstance();
		int flag = 0;
		// 禁止删除超级管理员
		if (!id.equals(10001)) {
			flag = userService.remove(id);
		}

		// 结果返回
		if (flag > 0) {
			r.setCode(Constants.SUCCESS_CODE);
			r.setMsg(Constants.SUCCESS_DESCRIPTION);
		} else {
			r.setCode(Constants.FAIL_CODE);
			r.setMsg(Constants.FAIL_DESCRIPTION);
		}
		return r;
	}

	/**
	 * id数组批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@PostMapping("/removeBatch")
	@ResponseBody
	public R removeBatch(@RequestBody String[] ids) {
		R r = R.getInstance();
		int flag = 0;
		// 禁止删除超级管理员
		if (!Arrays.asList(ids).contains("10001")) {
			flag = userService.removeBatch(ids);
		}

		// 结果返回
		if (flag > 0) {
			r.setCode(Constants.SUCCESS_CODE);
			r.setMsg(Constants.SUCCESS_DESCRIPTION);
		} else {
			r.setCode(Constants.FAIL_CODE);
			r.setMsg(Constants.FAIL_DESCRIPTION);
		}
		return r;
	}

	@GetMapping("/listMenu")
	@ResponseBody
	public List<Menu> listMenu(HttpSession session) {
		// 当前登录信息
		WebContext webContext = (WebContext) session.getAttribute(Constants.SESSION_USER);
		int roleId = webContext.getRoleId();
		// 获取该角色的系统菜单列表
		return menuService.listByRoleId(roleId);
	}

}

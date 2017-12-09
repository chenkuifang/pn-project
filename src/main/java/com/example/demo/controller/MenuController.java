package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.common.util.StringUtils;
import com.example.demo.entity.Menu;
import com.example.demo.service.CommonService;
import com.example.demo.service.MenuService;

/**
 * @author QuiFar
 * @version V1.0
 * @Description: 菜单管理控制器
 * @date 2017年11月26日 上午10:32:27
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	@Autowired
	private CommonService commonService;

	@RequestMapping(path = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		// 父级菜单列表
		List<Menu> parentMenus = menuService.listByParentId(0);
		model.addAttribute("parentMenus", parentMenus);

		return "/menu/list";
	}

	/**
	 * 根据条件获取菜单列表
	 *
	 * @param params
	 *            页面请求参数
	 * @return
	 */
	@GetMapping("/listPage")
	@ResponseBody
	public JsonResult listPage(@RequestParam Map<String, Object> params) {
		double page = Double.parseDouble(params.get("page").toString());
		double limit = Double.parseDouble(params.get("limit").toString());
		params.put("offset", (int) ((page - 1) * limit));

		List<Menu> list = menuService.listPage(params);
		int countPage = menuService.countPage(params);

		return JsonResultUtils.jsonPageResult(list, countPage);
	}

	/**
	 * 新增初始化视图 （参数验证的时候必须包括action字段）
	 *
	 * @param model
	 * @return 包含action判断页面是add/edit动作
	 */
	@GetMapping("/add")
	public String add(Model model) {
		// 初始值
		Menu menu = new Menu();
		menu.setIcon("icon-text");
		menu.setType(1);

		// 父级菜单列表
		List<Menu> parentMenus = menuService.listByParentId(0);
		model.addAttribute("menu", menu);
		model.addAttribute("parentMenus", parentMenus);
		model.addAttribute("action", "add");
		return "/menu/edit";
	}

	/**
	 * 编辑初始化视图
	 *
	 * @param model
	 * @return
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int menuId, Model model) {
		Menu menu = menuService.get(menuId);
		// 父级菜单列表
		List<Menu> parentMenus = menuService.listByParentId(0);

		model.addAttribute("menu", menu);
		model.addAttribute("parentMenus", parentMenus);
		model.addAttribute("action", "edit");
		return "/menu/edit";
	}

	/**
	 * 保存
	 *
	 * @param menu
	 * @return
	 */
	@PostMapping("/save")
	@ResponseBody
	public JsonResult save(Menu menu) {
		int flag;
		// 新增
		if (menu.getMenuId() == null) {
			int roleId = commonService.getTableNewId("pn_menu", "menu_id", 10001);
			menu.setMenuId(roleId);
			menu.setCreateTime(new Date());
			menu.setUpdateTime(new Date());
			flag = menuService.add(menu);
		} else {
			// role.setUpdateTime(new Date());
			flag = menuService.update(menu);
		}

		// 结果返回
		return JsonResultUtils.jsonResult(flag);
	}

	/**
	 * 根据ID移除单个对象
	 *
	 * @param menuId
	 * @return
	 */
	@PostMapping("/remove")
	@ResponseBody
	public JsonResult remove(@RequestParam("menuId") Integer menuId) {
		int flag = menuService.remove(menuId);

		// 结果返回
		return JsonResultUtils.jsonResult(flag);
	}

	/**
	 * id数组批量删除
	 *
	 * @param ids
	 * @return
	 */
	@PostMapping("/removeBatch")
	@ResponseBody
	public JsonResult removeBatch(@RequestBody String[] ids) {
		int flag = menuService.removeBatch(ids);

		// 结果返回
		return JsonResultUtils.jsonResult(flag);
	}

	/**
	 * 菜单列表
	 * 
	 * @param roleId
	 * @return
	 */
	@PostMapping("/listMenu/{roleId}")
	@ResponseBody
	public List<Map<String, Object>> listMenu(@PathVariable("roleId") int roleId) {
		List<Map<String, Object>> mapList = new ArrayList<>();
		List<Menu> menus = menuService.listCheckedByRoleId(roleId);
		Map<String, Object> map;
		for (Menu menu : menus) {
			map = new HashMap<>();
			map.put("id", menu.getMenuId());
			map.put("pId", menu.getParentId());
			map.put("name", menu.getName());
			if (!StringUtils.isEmpty(menu.getIsChecked())) {
				// 选中状态
				map.put("checked", true);
			}
			mapList.add(map);
		}

		return mapList;
	}

}

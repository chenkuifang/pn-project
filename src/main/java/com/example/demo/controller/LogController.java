package com.example.demo.controller;

import com.example.demo.common.Constants;
import com.example.demo.common.JsonResult;
import com.example.demo.common.util.JsonResultUtils;
import com.example.demo.entity.Log;
import com.example.demo.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统日志控制类
 *
 * @author QuiFar
 **/
@Controller
@RequestMapping("/log")
public class LogController {

	@Autowired
	private CommonService commonService;

	/**
	 * 系统日志初始视图
	 *
	 * @return 视图名称
	 */
	@RequestMapping(path = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		model.addAttribute("operations", Constants.OPERATIONS);
		return "/log/list";
	}

	/**
	 * 系统日志翻页列表
	 *
	 * @param params
	 *            页面请求参数
	 * @return
	 */
	@GetMapping("/listPage")
	@ResponseBody
	public JsonResult listPage(@RequestParam Map<String, Object> params) {
		List<Log> list = commonService.listPageLog(params);
		int countPage = commonService.countPageLog(params);

		return JsonResultUtils.jsonPageResult(list, countPage);
	}

}

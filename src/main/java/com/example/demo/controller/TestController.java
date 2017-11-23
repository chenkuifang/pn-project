package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 测试控制类
 * @author QuiFar
 * @date 2017年11月18日 下午4:21:41
 * @version V1.0
 */

@Controller
public class TestController {

	@GetMapping("/test")
	public void test() {

	}
	
	
}

package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatiesApplicationTests {
	@Autowired
	UserService userService;

	@Test
	public void contextLoads() {
		// System.err.println(123);
		User pnUser = userService.get(1);
		System.err.println(pnUser.toString());
	}

}

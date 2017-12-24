package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyApplicationTests {

	@Autowired
	private WebApplicationContext context;
	@Autowired
	UserService userService;

	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void contextLoads() {
		// User pnUser = userService.get(10001);
		// System.err.println(pnUser.toString());
	}

	@Test
	public void creatOrder() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders
				.post("order/createOrder")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				);

	}

}

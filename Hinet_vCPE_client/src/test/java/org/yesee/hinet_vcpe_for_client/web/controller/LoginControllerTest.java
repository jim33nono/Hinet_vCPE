package org.yesee.hinet_vcpe_for_client.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.yesee.hinet_vcpe_for_client.configuration.PocConfiguration;
import org.yesee.hinet_vcpe_for_client.configuration.PocWebConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { PocConfiguration.class, PocWebConfiguration.class })
public class LoginControllerTest {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webAppConfiguration;

	@Before
	public void setup(){
		 this.mockMvc =
				 MockMvcBuilders.webAppContextSetup(webAppConfiguration).build();
	}

	@Test
	public void test() throws Exception {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
		
		MvcResult mvcResult = mockMvc.perform(post("/login/index")).andReturn();
//		System.out.println(mvcResult.getResponse().getContentAsString());
	}

}

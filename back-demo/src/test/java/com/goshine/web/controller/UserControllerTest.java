package com.goshine.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get("/user").param("username", "lidejian")
				// .param("size", "15")
				// .param("page", "3").
				// param("sort", "age desc")
				.contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3)).andReturn().getResponse()
				.getContentAsString();

		System.out.println(result);

	}

	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$.username").value("tom")).andReturn().getResponse()
				.getContentAsString();
		System.out.println(result);

	}

	@Test
	public void whenGetInfoFail() throws Exception {
		String result = mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError()).andReturn().getResponse().getContentAsString();

		System.out.println(result);
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		Date data = new Date();
		System.out.println(data.getTime());
		String content = "{\"username\":\"tom\",\"password\":null, \"birthday\":"+ data.getTime() +"}";
		String result  = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		Date data = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(data.getTime());
		String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null, \"birthday\":"+ data.getTime() +"}";
		String result  = mockMvc.perform(put("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1)).andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	}

}

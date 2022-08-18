package com.example.httpsecurityconstruction;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HttpSecurityConstructionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void indexWhenUnAuthenticatedThenRedirect() throws Exception {
		// @formatter:off
		this.mockMvc.perform(get("/hello"))
				.andExpect(status().isUnauthorized());
		// @formatter:on
	}

	@Test
	@WithMockUser(username = "john")
	void indexWhenAuthenticatedThenOk() throws Exception {
		// @formatter:off
		this.mockMvc.perform(get("/hello"))
				.andExpect(status().isOk()).andExpect(content().string("Hello, john"));
		// @formatter:on
	}

}

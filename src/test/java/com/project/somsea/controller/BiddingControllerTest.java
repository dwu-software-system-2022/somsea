package com.project.somsea.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BiddingController.class)
public class BiddingControllerTest {
	@Autowired
	MockMvc mv;
	
	@Test
	@DisplayName("formcontroller")
	void testController() throws Exception {
		mv.perform(get("/biddingForm"))
		.andExpect(status().isOk());
	}
}

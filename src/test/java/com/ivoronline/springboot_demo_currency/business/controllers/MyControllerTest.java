package com.ivoronline.springboot_demo_currency.business.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class MyControllerTest {

  @Autowired MockMvc      mockMvc;
  @Autowired MyController myController;

  @Test
  void getAllCurrencyNames() throws Exception {

    //CREATE GET REQUEST
    //MockHttpServletRequestBuilder request = get("/GetAllCurrencyNames");

    //PERFORM REQUEST
    //mockMvc.perform(request).andExpect(status().isOk());

  }

  @Test
  void getFirstLastDate() {
  }

  @Test
  void getAverageValue() {
  }
}
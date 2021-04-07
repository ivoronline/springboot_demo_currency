package com.ivoronline.springboot_demo_currency.business.controllers;

import com.ivoronline.springboot_demo_currency.business.repositories.CurrencyRepository;
import com.ivoronline.springboot_demo_currency.loggerDB.repositories.LogRepository;
import com.ivoronline.springboot_demo_currency.securitydbauthorities.services.MyUserDetailsService;
import com.ivoronline.springboot_demo_currency.securityjwt.JWTUtil;
import com.ivoronline.springboot_demo_currency.securityjwt.MyAuthenticationManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = MyController.class)
@WithMockUser(username = "admin", authorities = { "MyService", "GetFirstLastDate", "GetAverageValue" })
class MyControllerTest {

  @Autowired MockMvc      mockMvc;
  @Autowired MyController myController;

  @MockBean CurrencyRepository      currencyRepository;
  @MockBean LogRepository           logRepository;
  @MockBean MyUserDetailsService    myUserDetailsService;
  @MockBean MyAuthenticationManager myAuthenticationManager;
  @MockBean JWTUtil                 JWTUtil;

  //===================================================================================
  // TEST  ENDPOINT URL: GET MyService
  //===================================================================================
  @Test
  void getAllCurrencyNames() throws Exception {
    mockMvc.perform(get("/GetAllCurrencyNames")).andExpect(status().isOk());
  }

  //===================================================================================
  // TEST  ENDPOINT URL: GET GetFirstLastDate
  //===================================================================================
  @Test
  void GetFirstLastDate() throws Exception {
    mockMvc.perform(get("/GetFirstLastDate?currencyName=EUR")).andExpect(status().isOk());
  }

  //===================================================================================
  // TEST  ENDPOINT URL: GET GetAverageValue
  //===================================================================================
  @Test
  void GetAverageValue() throws Exception {
    mockMvc.perform(get("/GetAverageValue?currencyName=EUR&startDate=2000-05-30&endDate=2010-05-30")).andExpect(status().isOk());
  }

}
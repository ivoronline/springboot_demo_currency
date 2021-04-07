package com.ivoronline.springboot_demo_currency.business.services;

import com.ivoronline.springboot_demo_currency.business.dto.AverageDTOResponse;
import com.ivoronline.springboot_demo_currency.business.dto.CurrenciesDTOResponse;
import com.ivoronline.springboot_demo_currency.business.dto.DatesDTOResponse;
import org.springframework.stereotype.Component;

@Component
public interface MyService {
  CurrenciesDTOResponse getAllCurrencyNames();
  DatesDTOResponse      getFirstLastDate(String currencyName);
  AverageDTOResponse    getAverageValue(String currencyName, String startDate, String endDate);
}

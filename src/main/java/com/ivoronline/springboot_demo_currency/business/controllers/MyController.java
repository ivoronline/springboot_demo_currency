package com.ivoronline.springboot_demo_currency.business.controllers;

import com.ivoronline.springboot_demo_currency.business.DTO.AverageDTO;
import com.ivoronline.springboot_demo_currency.business.DTO.CurrenciesDTO;
import com.ivoronline.springboot_demo_currency.business.DTO.DatesDTO;
import com.ivoronline.springboot_demo_currency.business.repositories.CurrencyRepository;
import com.ivoronline.springboot_demo_currency.loggerAOP.LogStartEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDate;

@Controller
public class MyController {

  @Autowired CurrencyRepository currencyRepository;

  //================================================================================
  // GET ALL CURRENCY NAMES
  //=================================================================================
  @LogStartEnd
  @ResponseBody
  @PreAuthorize("hasAuthority('GetAllCurrencyNames')")
  @RequestMapping("/GetAllCurrencyNames")
  public CurrenciesDTO getAllCurrencyNames()  {

    //CREATE DTO
    CurrenciesDTO currenciesDTO = new CurrenciesDTO();
               currenciesDTO.allCurrencyNames = currencyRepository.getAllCurrencyNames();

    //RETURN SOMETHING TO BROWSER
    return currenciesDTO;

  }

  //================================================================================
  // GET FIRST LAST DATE
  //================================================================================
  @LogStartEnd
  @ResponseBody
  @PreAuthorize("hasAuthority('GetFirstLastDate')")
  @RequestMapping("/GetFirstLastDate")
  public DatesDTO getFirstLastDate(@RequestParam String currencyName)  {

    //CREATE DTO
    DatesDTO datesDTO = new DatesDTO();
          datesDTO.firstDate = currencyRepository.getFirstDate(currencyName);
          datesDTO.lastDate  = currencyRepository.getLastDate (currencyName);

    //RETURN SOMETHING TO BROWSER
    return datesDTO;

  }

  //================================================================================
  // GET AVERAGE VALUE
  //================================================================================
  @LogStartEnd
  @ResponseBody
  @PreAuthorize("hasAuthority('GetAverageValue')")
  @RequestMapping("/GetAverageValue")
  public AverageDTO getAverageValue(
    @RequestParam String currencyName,
    @RequestParam String startDateString,
    @RequestParam String endDateString
  )  {

    //CONVERT DATES
    LocalDate startDate = LocalDate.parse(startDateString);
    LocalDate endDate   = LocalDate.parse(endDateString);

    //CREATE DTO
    AverageDTO averageDTO = new AverageDTO();
            averageDTO.average = currencyRepository.getAverageValue(currencyName, startDate, endDate);

    //RETURN AVERAGE VALUE
    return averageDTO;

  }

}

package com.ivoronline.springboot_demo_currency.functionality.controllers;

import com.ivoronline.springboot_demo_currency.functionality.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MyController {

  @Autowired CurrencyRepository currencyRepository;

  //================================================================================
  // GET ALL CURRENCY NAMES
  //================================================================================
  @ResponseBody
  @PreAuthorize("hasAuthority('GetAllCurrencyNames')")
  @RequestMapping("/GetAllCurrencyNames")
  public List<String> getAllCurrencyNames()  {

    //STORE ENTITY
    List<String> allCurrencyNames = currencyRepository.getAllCurrencyNames();

    //RETURN SOMETHING TO BROWSER
    return allCurrencyNames;

  }

  //================================================================================
  // GET FIRST LAST DATE
  //================================================================================
  @ResponseBody
  @PreAuthorize("hasAuthority('GetFirstLastDate')")
  @RequestMapping("/GetFirstLastDate")
  public LocalDate[] getFirstLastDate(@RequestParam String currencyName)  {

    LocalDate firstDate = currencyRepository.getFirstDate(currencyName);
    LocalDate lastDate  = currencyRepository.getLastDate(currencyName);

    LocalDate[] dates = {firstDate, lastDate};

    //RETURN SOMETHING TO BROWSER
    return dates;

  }

  //================================================================================
  // GET AVERAGE VALUE
  //================================================================================
  @ResponseBody
  @PreAuthorize("hasAuthority('GetAverageValue')")
  @RequestMapping("/GetAverageValue")
  public Float getAverageValue(
    @RequestParam String currencyName,
    @RequestParam String startDateString,
    @RequestParam String endDateString
  )  {

    //CONVERT DATES
    LocalDate startDate = LocalDate.parse(startDateString);
    LocalDate endDate   = LocalDate.parse(endDateString);

    //GET AVERAGE VALUE
    Float avg = currencyRepository.getAverageValue(currencyName, startDate, endDate);

    //RETURN AVERAGE VALUE
    return avg;

  }

}
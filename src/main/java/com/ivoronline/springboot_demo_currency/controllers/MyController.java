package com.ivoronline.springboot_demo_currency.controllers;

import com.ivoronline.springboot_demo_currency.entities.Currency;
import com.ivoronline.springboot_demo_currency.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
  //=================================================================================
  @ResponseBody
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

  //================================================================================
  // ADD CURRENCY (HELPER)
  //================================================================================
  @ResponseBody
  @PostMapping("/AddCurrency")
  public String addCurrency(@RequestBody Currency currency)  {

    //REFORMAT EXCHANGE RATE
    currency.exchangeRate = Double.parseDouble(currency.exchangeRateString.replace(",", "."));

    //STORE ENTITY
    currencyRepository.save(currency);

    //RETURN SOMETHING TO BROWSER
    return "Currency added to DB";

  }

  //================================================================================
  // HELLO (HELPER)
  //================================================================================
  @ResponseBody
  @RequestMapping("/Hello")
  public String hello() {
    return "Hello from Controller";
  }

}

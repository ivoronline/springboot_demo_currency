package com.ivoronline.springboot_demo_currency.business.controllers;

import com.ivoronline.springboot_demo_currency.business.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MyController {

  @Autowired CurrencyRepository currencyRepository;

  //================================================================================
  // GET ALL CURRENCY NAMES
  //=================================================================================
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
  public LocalDate[] getFirstLastDate(@RequestParam @NotBlank String currencyName)  {

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
    @RequestParam @NotBlank String currencyName,
    @RequestParam @NotBlank String startDate,
    @RequestParam @NotBlank String endDate
  )  {

    //CONVERT DATES
    LocalDate startDateConverted = LocalDate.parse(startDate);
    LocalDate endDateConverted   = LocalDate.parse(endDate);

    //GET AVERAGE VALUE
    Float avg = currencyRepository.getAverageValue(currencyName, startDateConverted, endDateConverted);

    //RETURN AVERAGE VALUE
    return avg;

  }

  //==================================================================
  // HANDLE EXCEPTIONS (it only catches first exception)
  //==================================================================
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public String handleExceptions(MissingServletRequestParameterException exception) {

    //GET EXCEPTION DETAILS
    String parameterType = exception.getParameterType(); //String
    String parameterName = exception.getParameterName(); //name
    String message       = exception.getMessage();       //Required String parameter 'name' is not present

    //RETURN MESSAGE
    return message;

  }

}

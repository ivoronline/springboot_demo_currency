package com.ivoronline.springboot_demo_currency.business.services;

import com.ivoronline.springboot_demo_currency.business.dto.AverageDTOResponse;
import com.ivoronline.springboot_demo_currency.business.dto.CurrenciesDTOResponse;
import com.ivoronline.springboot_demo_currency.business.dto.DatesDTOResponse;
import com.ivoronline.springboot_demo_currency.business.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MyServiceImpl implements MyService {

  @Autowired CurrencyRepository currencyRepository;

  //================================================================================
  // GET ALL CURRENCY NAMES
  //================================================================================
  @Override
  public CurrenciesDTOResponse getAllCurrencyNames() {

    //GET CURRENCY NAMES
    List<String> allCurrencyNames = currencyRepository.getAllCurrencyNames();

    //CREATE DTO
    CurrenciesDTOResponse currenciesDTOResponse = new CurrenciesDTOResponse();
                          currenciesDTOResponse.allCurrencyNames = allCurrencyNames;

    //RETURN DTO
    return currenciesDTOResponse;

  }

  //================================================================================
  // GET FIRST LAST DATE
  //================================================================================
  @Override
  public DatesDTOResponse getFirstLastDate(String currencyName) {

    //GET DATES
    LocalDate firstDate = currencyRepository.getFirstDate(currencyName);
    LocalDate lastDate  = currencyRepository.getLastDate (currencyName);

    //CREATE DTO
    DatesDTOResponse datesDTOResponse = new DatesDTOResponse();
                     datesDTOResponse.firstDate = firstDate;
                     datesDTOResponse.lastDate  = lastDate;

    //RETURN DTO
    return datesDTOResponse;

  }

  //================================================================================
  // GET AVERAGE VALUE
  //================================================================================
  @Override
  public AverageDTOResponse getAverageValue(String currencyName, String startDate, String endDate) {

    //CONVERT DATES
    LocalDate startDateConverted = LocalDate.parse(startDate);
    LocalDate endDateConverted   = LocalDate.parse(endDate);

    //GET AVERAGE VALUE
    Float average = currencyRepository.getAverageValue(currencyName, startDateConverted, endDateConverted);

    //CREATE DTO
    AverageDTOResponse averageDTOResponse = new AverageDTOResponse();
                       averageDTOResponse.average = average;

    //RETURN DTO
    return averageDTOResponse;

  }

}

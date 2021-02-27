package com.ivoronline.springboot_demo_currency.repositories;

import com.ivoronline.springboot_demo_currency.entities.Currency;
import com.ivoronline.springboot_demo_currency.entities.CurrencyId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface  CurrencyRepository extends CrudRepository<Currency, CurrencyId> {

  //GET ALL CURRENCY NAMES
  @Query(nativeQuery = true, value = "SELECT DISTINCT CURRENCY_NAME FROM CURRENCY")
  List<String> getAllCurrencyNames();

  //GET FIRST ENTRY
  @Query(nativeQuery = true, value = "SELECT MIN(date) FROM currency WHERE currency_name = :currencyName")
  LocalDate getFirstDate(String currencyName);

  //GET LAST ENTRY
  @Query(nativeQuery = true, value = "SELECT MAX(date) FROM currency WHERE currency_name = :currencyName")
  LocalDate getLastDate(String currencyName);

  //GET AVERAGE VALUE
  @Query(nativeQuery = true, value = "SELECT AVG(exchange_rate) FROM currency WHERE currency_name = :currencyName AND date>=:startDate AND date<=:endDate")
  Float getAverageValue(String currencyName, LocalDate startDate, LocalDate endDate);

}



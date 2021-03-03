package com.ivoronline.springboot_demo_currency.business.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class CurrencyId implements Serializable {

  //COMPOSITE PRIMARY KEY
  private String    currencyName;
  private LocalDate date;

  //REQUIERED NO ARGS CONSTRUCTOR
  public CurrencyId() { }

  //CONSTRUCTOR FOR findById(BookId)
  public CurrencyId(String currencyName, LocalDate date) {
    this.currencyName = currencyName;
    this.date         = date;
  }

}

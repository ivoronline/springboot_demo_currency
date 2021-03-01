package com.ivoronline.springboot_demo_currency.functionality.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Transient;
import java.time.LocalDate;

@Data
@Entity
@IdClass(CurrencyId.class)
public class Currency {

  //COMPOSITE PRIMARY KEY
  @Id @JsonProperty("valuta")         public String    currencyName;
  @Id @JsonProperty("datum_primjene") public LocalDate date;

  //STORED PROPERTIES
  @JsonProperty("broj_tecajnice")     public Integer   number;
  @JsonProperty("drzava")             public String    state;
  @JsonProperty("drzava_iso")         public String    stateISO;
  @JsonProperty("sifra_valute")       public String    currencyCode;
  @JsonProperty("jedinica")           public Integer   units;
                                      public Double    exchangeRate;

  //TRANSIENT PROPERTIES
  @Transient
  @JsonProperty("srednji_tecaj")
  public String exchangeRateString;

}

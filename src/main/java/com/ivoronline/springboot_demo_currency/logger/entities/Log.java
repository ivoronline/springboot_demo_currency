package com.ivoronline.springboot_demo_currency.logger.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Log {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer   id;
  public String    protocol;
  public String    serverName;
  public String    method;
  public String    servletPath;
  public String    queryString;
  public Integer   serverPort;
  public String    username;
  public Integer   status;
  public LocalDate date;

}

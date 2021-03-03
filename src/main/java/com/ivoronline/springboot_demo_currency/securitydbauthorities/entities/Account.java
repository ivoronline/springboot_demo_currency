package com.ivoronline.springboot_demo_currency.securitydbauthorities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  public String  username;
  public String  password;

  //FOREIGN KEY
  public String profile;

}

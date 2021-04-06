package com.ivoronline.springboot_demo_currency.securitydbauthorities.startup;

import com.ivoronline.springboot_demo_currency.securitydbauthorities.entities.Authority;
import com.ivoronline.springboot_demo_currency.securitydbauthorities.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(1)
public class AuthorityLoader implements CommandLineRunner {

  @Autowired private AuthorityRepository authorityRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    authorityRepository.save(new Authority("GetAllCurrencyNames"));
    authorityRepository.save(new Authority("GetFirstLastDate"));
    authorityRepository.save(new Authority("GetAverageValue"));
    authorityRepository.save(new Authority("GetLog"));
  }

}

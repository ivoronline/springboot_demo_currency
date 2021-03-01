package com.ivoronline.springboot_demo_currency.security.config;

import com.ivoronline.springboot_demo_currency.security.entities.Authority;
import com.ivoronline.springboot_demo_currency.security.entities.Profile;
import com.ivoronline.springboot_demo_currency.security.repositories.AuthorityRepository;
import com.ivoronline.springboot_demo_currency.security.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(2)
public class ProfileLoader implements CommandLineRunner {

  @Autowired private ProfileRepository profileRepository;
  @Autowired private AuthorityRepository authorityRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    //GET AUTHORITIES.
    Authority getAllCurrencyNames = authorityRepository.findById("GetAllCurrencyNames").get();
    Authority getFirstLastDate    = authorityRepository.findById("GetFirstLastDate"   ).get();
    Authority getAverageValue     = authorityRepository.findById("GetAverageValue"    ).get();

    //USER
    Profile user      = new Profile();
            user.name = "USER";
            user.authorities.add(getAllCurrencyNames);

    //ADMIN
    Profile admin      = new Profile();
            admin.name = "ADMIN";
            admin.authorities.add(getAllCurrencyNames);
            admin.authorities.add(getFirstLastDate);
            admin.authorities.add(getAverageValue);

    //STORE PROFILES INTO DB
    profileRepository.save(user);
    profileRepository.save(admin);

  }

}

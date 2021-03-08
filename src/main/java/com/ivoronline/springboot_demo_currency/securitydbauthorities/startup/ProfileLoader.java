package com.ivoronline.springboot_demo_currency.securitydbauthorities.startup;

import com.ivoronline.springboot_demo_currency.securitydbauthorities.entities.Authority;
import com.ivoronline.springboot_demo_currency.securitydbauthorities.entities.Profile;
import com.ivoronline.springboot_demo_currency.securitydbauthorities.repositories.AuthorityRepository;
import com.ivoronline.springboot_demo_currency.securitydbauthorities.repositories.ProfileRepository;
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
    Authority getLog              = authorityRepository.findById("GetLog"             ).get();

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
            admin.authorities.add(getLog);

    //STORE PROFILES INTO DB
    profileRepository.save(user);
    profileRepository.save(admin);

  }

}

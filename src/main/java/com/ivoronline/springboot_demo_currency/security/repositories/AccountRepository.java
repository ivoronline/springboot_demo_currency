package com.ivoronline.springboot_demo_currency.security.repositories;

import com.ivoronline.springboot_demo_currency.security.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
  Account findByUsername(String Username);
}

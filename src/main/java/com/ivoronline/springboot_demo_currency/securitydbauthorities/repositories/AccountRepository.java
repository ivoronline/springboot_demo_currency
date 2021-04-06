package com.ivoronline.springboot_demo_currency.securitydbauthorities.repositories;

import com.ivoronline.springboot_demo_currency.securitydbauthorities.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}

package com.ivoronline.springboot_demo_currency.securitydbauthorities.repositories;

import com.ivoronline.springboot_demo_currency.securitydbauthorities.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> { }

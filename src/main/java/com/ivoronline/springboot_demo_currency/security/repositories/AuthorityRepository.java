package com.ivoronline.springboot_demo_currency.security.repositories;

import com.ivoronline.springboot_demo_currency.security.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> { }

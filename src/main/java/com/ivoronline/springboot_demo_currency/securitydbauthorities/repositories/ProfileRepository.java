package com.ivoronline.springboot_demo_currency.securitydbauthorities.repositories;

import com.ivoronline.springboot_demo_currency.securitydbauthorities.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> { }

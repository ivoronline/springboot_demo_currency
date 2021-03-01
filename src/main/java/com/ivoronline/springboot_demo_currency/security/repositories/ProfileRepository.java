package com.ivoronline.springboot_demo_currency.security.repositories;

import com.ivoronline.springboot_demo_currency.security.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> { }

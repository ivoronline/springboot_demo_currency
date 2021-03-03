package com.ivoronline.springboot_demo_currency.securityjwt;

import com.ivoronline.springboot_demo_currency.securitydbauthorities.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationManager implements AuthenticationManager {

  @Autowired MyUserDetailsService myUserDetailsService;

  @Override
  public Authentication authenticate(Authentication enteredAuthentication) {

    //GET ENTERED CREDENTIALS
    String enteredUsername = (String) enteredAuthentication.getPrincipal();   //ENTERED USERNAME
    String enteredPassword = (String) enteredAuthentication.getCredentials(); //ENTERED PASSWORD

    //GET STORED CREDENTIALS
    UserDetails userDetails = myUserDetailsService.loadUserByUsername(enteredUsername);

    //AUTHENTICATE USER (COMPARE ENTERED AND STORED CREDENTIALS)
    if ( userDetails == null                              ) { System.out.println("Username not found"); return null; }
    if (!enteredPassword.equals(userDetails.getPassword())) { System.out.println("Incorrect Password"); return null; }

    //CREATE VALIDATED AUTHENTICATION
    Authentication validatedAuth = new UsernamePasswordAuthenticationToken(enteredUsername, null, userDetails.getAuthorities());

    //RETURN VALIDATED AUTHENTICATION
    return validatedAuth;

  }

}

package com.ivoronline.springboot_demo_currency.securityjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.constraints.NotBlank;

@Controller
public class JWTController {

  @Autowired JWTUtil                 jwtUtil;
  @Autowired MyAuthenticationManager myAuthenticationManager;

  //==================================================================
  // GET JWT
  //==================================================================
  @ResponseBody
  @RequestMapping("/GetJWT")
  public String getJWT(@RequestParam @NotBlank String enteredUsername, @RequestParam @NotBlank String enteredPassword) {

    //AUTHENTICATE (COMPARE ENTERED AND STORED CREDENTIALS)
    Authentication enteredAuth  = new UsernamePasswordAuthenticationToken(enteredUsername, enteredPassword);
    Authentication returnedAuth = myAuthenticationManager.authenticate(enteredAuth);

    //CHECK RESULT OF AUTHENTICATION
    if(returnedAuth == null) { return "User is NOT Authenticated"; }

    //CREATE JWT
    String username    = (String) returnedAuth.getPrincipal();
    String authorities = (String) returnedAuth.getAuthorities().toString(); //"[CREATE, READ]"
    String jwt         = jwtUtil.createJWT(username, authorities);

    //RETURN JWT
    return jwt;

  }

  //==================================================================
  // HANDLE EXCEPTIONS (it only catches first exception)
  //==================================================================
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public String handleExceptions(MissingServletRequestParameterException exception) {

    //GET EXCEPTION DETAILS
    String parameterType = exception.getParameterType(); //String
    String parameterName = exception.getParameterName(); //name
    String message       = exception.getMessage();       //Required String parameter 'name' is not present

    //RETURN MESSAGE
    return message;

  }

}
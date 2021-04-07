package com.ivoronline.springboot_demo_currency.loggerDB.filters;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ivoronline.springboot_demo_currency.loggerDB.entities.Log;
import com.ivoronline.springboot_demo_currency.loggerDB.repositories.LogRepository;
import com.ivoronline.springboot_demo_currency.securitydbauthorities.services.MyUserDetailsService;
import org.hibernate.annotations.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LogFilter extends OncePerRequestFilter {

  @Autowired LogRepository logRepository;
  @Autowired MyUserDetailsService myUserDetailsService;

  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws IOException, ServletException {

    System.out.println("FILTER");

    //DIVIDES HTTP REQUEST AND RESPONSE CODE
    chain.doFilter(request, response);

    //GET USERNAME
    String username;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) { username = ((UserDetails)principal).getUsername(); }
    else                                  { username = principal.toString();                   }

    //CREATE LOG
    Log log             = new Log();
        log.username    = username;
        log.date        = LocalDate.now();
        log.protocol    = request.getProtocol();
        log.serverName  = request.getServerName();
        log.serverPort  = request.getServerPort();
        log.servletPath = request.getServletPath();
        log.queryString = request.getQueryString();
        log.method      = request.getMethod();
        log.status      = response.getStatus();

    //STORE LOG
    logRepository.save(log);

  }

}

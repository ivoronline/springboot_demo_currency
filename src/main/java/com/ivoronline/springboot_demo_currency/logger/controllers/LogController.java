package com.ivoronline.springboot_demo_currency.logger.controllers;

import com.ivoronline.springboot_demo_currency.logger.entities.Log;
import com.ivoronline.springboot_demo_currency.logger.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LogController {

  @Autowired LogRepository logRepository;

  //================================================================================
  // GET LOG
  //=================================================================================
  @ResponseBody
  @PreAuthorize("hasAuthority('GetLog')")
  @RequestMapping("/GetLog")
  public List<Log> getLog(@RequestParam String username,
                          @RequestParam String dateStart,
                          @RequestParam String dateEnd)
  {

    //CONVERT DATES
    LocalDate startDateConverted = LocalDate.parse(dateStart);
    LocalDate endDateConverted   = LocalDate.parse(dateEnd);

    //GET LOG
    List<Log> logs = logRepository.getLog(username, startDateConverted, endDateConverted);

    //RETURN SOMETHING TO BROWSER
    return logs;

  }
}

package com.ivoronline.springboot_demo_currency.business.scheduledtasks;

import com.ivoronline.springboot_demo_currency.business.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableScheduling
public class ScheduledTasks {

  @Autowired CurrencyService currencyService;

  @Value("${loadCurrentYear}")
  private Boolean loadCurrentYear;

  //=========================================================================
  // LOAD CURRENT YEAR (EVERY 24h)
  //=========================================================================
  @Scheduled(fixedDelay = 24 * 60 * 60 * 1000, initialDelay = 1000)
  public void loadCurrentYear() throws InterruptedException {

    //RETURN IF CURRENT YEAR SHOULD NOT BE LOADED
    if(!loadCurrentYear) { return; }

    //LOG
    System.out.println("STARTED TASK: loadCurrentYear() ----------------------------------");

    //CONSTRUCT URL
    Date    currebtDate = new Date();
    Integer year        = 1900 + currebtDate.getYear();
    String  url         = "http://api.hnb.hr/tecajn/v2?datum-primjene-od=" + year + "-01-01&datum-primjene-do=" + year + "-12-31";

    //GET CURRENCIES FOR CURRENT YEAR
    System.out.print(year + " " + url + " ... ");
    currencyService.storeCurrencies(url);
    System.out.println("DONE");

    //LOG
    System.out.println("ENDED TASK: loadCurrentYear() ----------------------------------");

  }

}

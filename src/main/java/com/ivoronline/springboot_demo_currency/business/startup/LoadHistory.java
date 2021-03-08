package com.ivoronline.springboot_demo_currency.business.startup;

import com.ivoronline.springboot_demo_currency.business.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadHistory implements CommandLineRunner {

  @Autowired CurrencyService currencyService;

  @Value("${loadHistory}")
  private Boolean loadHistory;

  //=====================================================================================
  // RUN
  //=====================================================================================
  @Override
  public void run(String... args) throws Exception {

    //RETURN IF HISTORY SHOULD NOT BE LOADED
    if(!loadHistory) { return; }

    //LOG
    System.out.println("STARTED RUNNER: LoadHistory ----------------------------------");

    //CONSTRUCT URL
    Integer sleep   = 10 * 1000;   //Sleep for 10 seconds
    Integer year    = 1994;
    Integer yearEnd = 2020;
    while(year <= yearEnd) {
      String url = "http://api.hnb.hr/tecajn/v2?datum-primjene-od=" + year + "-01-01&datum-primjene-do=" + year + "-12-31";
      System.out.print(year + " " + url + " ... ");
      currencyService.storeCurrencies(url);
      System.out.println("DONE");
      Thread.sleep(sleep);
      year++;
    }

    //LOG
    System.out.println("ENDED RUNNER: LoadHistory ----------------------------------");

  }

}

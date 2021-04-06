package com.ivoronline.springboot_demo_currency.loggerDB.repositories;

import com.ivoronline.springboot_demo_currency.loggerDB.entities.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface LogRepository extends CrudRepository<Log, Integer> {

  //GET LOG
  @Query(value = "SELECT log FROM Log log WHERE log.username = :username AND log.date >= :startDate AND log.date <= :endDate")
  List<Log> getLog(String username, LocalDate startDate, LocalDate endDate);

}

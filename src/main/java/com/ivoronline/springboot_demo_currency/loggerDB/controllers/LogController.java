package com.ivoronline.springboot_demo_currency.loggerDB.controllers;

import com.ivoronline.springboot_demo_currency.loggerDB.entities.Log;
import com.ivoronline.springboot_demo_currency.loggerDB.repositories.LogRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.validation.constraints.NotBlank;
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
  public ResponseEntity<StreamingResponseBody>  getLog(
    @RequestParam @NotBlank String username,
    @RequestParam @NotBlank String startDate,
    @RequestParam @NotBlank String endDate
  ) {

    //CONVERT DATES
    LocalDate startDateConverted = LocalDate.parse(startDate);
    LocalDate endDateConverted   = LocalDate.parse(endDate);

    //GET LOG
    List<Log> logs = logRepository.getLog(username, startDateConverted, endDateConverted);

    //CREATE EXCEL FILE
    Workbook workBook = new XSSFWorkbook();

    //CREATE TABLE
    Sheet    sheet = workBook.createSheet("My Sheet");
             sheet.setColumnWidth(0, 12 * 256);        //10 characters wide
             sheet.setColumnWidth(1, 12 * 256);        //10 characters wide
             sheet.setColumnWidth(2, 12 * 256);        //10 characters wide
             sheet.setColumnWidth(3, 12 * 256);        //10 characters wide
             sheet.setColumnWidth(4, 12 * 256);        //10 characters wide
             sheet.setColumnWidth(5, 12 * 256);        //10 characters wide
             sheet.setColumnWidth(6, 10 * 256);        //10 characters wide
             sheet.setColumnWidth(7, 40 * 256);        //10 characters wide
             sheet.setColumnWidth(8, 90 * 256);        //10 characters wide

    //CREATE HEADER
    Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("DATE");
        header.createCell(1).setCellValue("USERNAME");
        header.createCell(2).setCellValue("STATUS");
        header.createCell(3).setCellValue("METHOD");
        header.createCell(4).setCellValue("PROTOCOL");
        header.createCell(5).setCellValue("SERVER");
        header.createCell(6).setCellValue("PORT");
        header.createCell(7).setCellValue("PATH");
        header.createCell(8).setCellValue("QUERY");

    //POPULATE TABLE
    int rowIndex = 1;
    for(Log log : logs) {

      //CREATE ROW
      Row row = sheet.createRow(rowIndex++);

      //CREATE CELLS
      row.createCell(0).setCellValue(log.date.toString());
      row.createCell(1).setCellValue(log.username);
      row.createCell(2).setCellValue(log.status);
      row.createCell(3).setCellValue(log.method);
      row.createCell(4).setCellValue(log.protocol);
      row.createCell(5).setCellValue(log.serverName);
      row.createCell(6).setCellValue(log.serverPort);
      row.createCell(7).setCellValue(log.servletPath);
      row.createCell(8).setCellValue(log.queryString);

    }

    //DOWNLOAD EXCEL
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"Log.xlsx\"")
      .body(workBook::write);

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

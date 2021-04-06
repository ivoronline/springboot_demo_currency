package com.ivoronline.springboot_demo_currency.loggerAOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogStartEndAspect {

  @Around("@annotation(LogStartEnd)")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

    //GET METHOD PARAMETERS
    String methodName = joinPoint.getSignature().getName();                          //hello
    String className  = joinPoint.getSignature().getDeclaringType().getSimpleName(); //MyController

    //EXECUTE BEFORE METHOD
    log.info("STARTED METHOD: " + className + "." + methodName + "()");

    //EXECUTE METHOD
    Object proceed = joinPoint.proceed();

    //EXECUTE AFTER METHOD
    log.info("ENDED   METHOD: " + className + "." + methodName + "()");

    //RETURN METHOD RESULT
    return proceed;

  }

}

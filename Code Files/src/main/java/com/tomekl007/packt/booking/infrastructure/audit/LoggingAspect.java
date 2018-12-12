package com.tomekl007.packt.booking.infrastructure.audit;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {
    static Logger log = Logger.getLogger(LoggingAspect.class.getName());


    @Before("execution(* com.tomekl007.packt.booking.api.BookingService.book(..))")
    public void logBookingRequest(JoinPoint joinPoint) {
        log.info("before booking request with arguments: " + Arrays.toString(joinPoint.getArgs()));
    }
}

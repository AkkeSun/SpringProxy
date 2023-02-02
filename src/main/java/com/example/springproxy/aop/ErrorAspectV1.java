package com.example.springproxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ErrorAspectV1 {


    @Around("execution(* com.example.springproxy.service..*(..))")
    public Object logAspect(ProceedingJoinPoint joinPoint) throws Throwable{

        log.info("[AOP START] {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}

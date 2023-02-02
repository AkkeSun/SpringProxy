package com.example.springproxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    target method 파라미터를 가져오논 예제 (joinPoint.getArgs() 사용)
 */
@Aspect
//@Component
@Slf4j
public class AspectV4 {

    @Pointcut("execution(* com.example.springproxy.service..*(..))")
    private void point(){}

    @Around("point()")
    public Object aspect(ProceedingJoinPoint joinPoint) throws Throwable{

        String methodName = joinPoint.getSignature().getName();

        // 매소드의 파라미터가 순서대로 저장된다
        Object [] args =  joinPoint.getArgs();
        String param = "";
        for (Object arg : args) {
            if(arg instanceof String){
                param = (String) arg;
            }
        }

        log.info("[AOP START] {}, {}", methodName, param);
        Object retVal = joinPoint.proceed();
        log.info("[AOP END] {}, {}", methodName, param);
        return retVal;
    }
}

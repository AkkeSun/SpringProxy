package com.example.springproxy.aop;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
//@Component
public class AspectV1 {

    // service 하위에 있는 모든 클래스의 모든 메소드에 적용
    @Pointcut("execution(* com.example.springproxy.service..*(..))")
    private void point(){}

    @Around("point()")
    //@Around("execution(* com.example.springproxy.service.*(..))")
    public Object logAspect(ProceedingJoinPoint joinPoint) throws Throwable{

        //---------타겟 메서드 실행 전----------
        log.info("[AOP START] {}", joinPoint.getSignature());

        //------------------------------------
        Object retVal = joinPoint.proceed();
        //------------------------------------

        //---------타겟 메서드 실행 후-----------
        log.info("[AOP END] {}", retVal);

        return retVal;
    }
}

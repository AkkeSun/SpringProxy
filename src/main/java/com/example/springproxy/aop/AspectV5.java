package com.example.springproxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
    target method 파라미터를 가져오논 예제 (args 사용)
 */
@Aspect
//@Component
@Slf4j
public class AspectV5 {

    @Pointcut("execution(* com.example.springproxy.service..*(..))")
    private void point(){}

    /*
        args() 를 이용한 파라미터 매핑
        괄호 안에 식은 execution 식의 파라미터 식과 같다.
        단, 작성된 값이 데이터 타입이 아니라 필드명이며, 지정한 필드명으로 aspect 에서 매칭해야한다.
        args() 를 사용하려면 AOP를 적용하고자 하는 메소드의 입력 파라미터 순서를 맞출 필요가 있다.
     */
    @Around("point() && args(data, ..)")
    public Object aspect(ProceedingJoinPoint joinPoint, String data) throws Throwable{

        String methodName = joinPoint.getSignature().getName();

        log.info("[AOP START] {}, {}", methodName, data);
        Object retVal = joinPoint.proceed();
        log.info("[AOP END] {}, {}", methodName, data);
        return retVal;
    }
}

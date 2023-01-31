package com.example.springproxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
//@Component
public class AspectV2 {

    // service 하위에 있는 모든 클래스의 모든 메소드
    @Pointcut("execution(* com.example.springproxy.service..*(..))")
    private void point(){}

    // 클래스 이름 패턴이 *Service 의 모든 메소드
    @Pointcut("execution(* *..*Service..*(..))")
    private void point2(){}


    @Around("point() && point2()")
    public Object logAspect(ProceedingJoinPoint joinPoint) throws Throwable{

        try{
            log.info("[트랜젝션 시작] {}", joinPoint.getSignature());
            Object retVal = joinPoint.proceed();
            log.info("[트랜젝션 커멧] {}", joinPoint.getSignature());
            return retVal;
        } catch (Exception e) {
            log.info("[트랜젝션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}

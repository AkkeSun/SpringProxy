package com.example.springproxy.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
    AOP 순서 부여 예제 - Order 를 사용한다
    order 는 class Level 로 실행되므로 두 개의 AOP 를 실행하려면 클래스를 분리해야한다. (내부클리스를 사용하면 간편)
 */
@Slf4j
public class AspectV3 {

    @Aspect
    @Order(0) // 먼저 시작
//    @Component
    public static class LosAspect{

        @Around("com.example.springproxy.aop.PointCuts.point3()")
        public Object aspect(ProceedingJoinPoint joinPoint) throws Throwable{
            log.info("[AOP START] {}", joinPoint.getSignature());
            Object retVal = joinPoint.proceed();
            log.info("[AOP END] {}", retVal);
            return retVal;
        }
    }

    @Aspect
    @Order(1)
//    @Component
    public static class TxAspect{

        @Around("com.example.springproxy.aop.PointCuts.point3()")
        public Object aspect(ProceedingJoinPoint joinPoint) throws Throwable{
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
}

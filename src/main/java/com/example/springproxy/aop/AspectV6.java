package com.example.springproxy.aop;

import com.example.springproxy.aop.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectV6 {

    // 어노테이션을 pointCut 으로 사용하는 경우 파라미터로 어노테이션을 주입받으면 풀 패키지명을 쓰지 않아도 된다
    // @Around("@annotation(com.example.springproxy.aop.annotation.Retry)")
    @Around("@annotation(retry)")
    public Object logAspect(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable{

        int maxRetry = retry.value();
        Exception exceptionHolder = null;
        for(int retryCnt = 1; retryCnt <= maxRetry; retryCnt ++) {
            try{
                log.info("[retry] try count={}/{}", retryCnt, maxRetry);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }
        throw exceptionHolder;
    }
}

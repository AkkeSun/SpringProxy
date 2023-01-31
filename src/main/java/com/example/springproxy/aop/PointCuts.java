package com.example.springproxy.aop;

import org.aspectj.lang.annotation.Pointcut;

/*
   execution
   () : 파라미터가 없는 메소드
   (*) : 정확히 하나의 파라미터. 단 모든 타입을 허용
   (*, *) : 정확히 두 개의 파라미터. 단 모든 타입을 허용한다
   (..) : 모든 메소드
   (String, ..) : 파라미터가 String 타입으로 시작
 */
public class PointCuts {

    // service 하위에 있는 모든 클래스의 모든 메소드
    @Pointcut("execution(* com.example.springproxy.service..*(..))")
    public void point(){}

    // 클래스 이름 패턴이 *Service 의 모든 메소드
    @Pointcut("execution(* *..*Service..*(..))")
    public void point2(){}

    // 두 개의 포인트컷을 합칠 수도 있따
    @Pointcut("point() && point()2")
    public void point3(){};

    // 어노테이션으로 포인트컷을 주는 경우 -> AOP 를 적용하고자 하는 메소드에 해당 어노테이션을 달아준다
    @Pointcut("@annotation(com.example.springproxy.aop.annotation.PerfLogging)")
    public void point4(){};

}

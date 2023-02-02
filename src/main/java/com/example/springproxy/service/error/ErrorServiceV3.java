package com.example.springproxy.service.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    내부 매서드에도 AOP 가 작동하도록 수정 2 - 지연로딩
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ErrorServiceV3 {

    // ObjectProvider
    // 스프링 빈 생성 시점에 빈을 조회하는것이 아니라 실제 빈을 사용할 때 조회하도록 해주는 객채
    private final ObjectProvider<ErrorServiceV3> errorServiceProvider;

    public void external() {
        log.info("call external");
        ErrorServiceV3 errorServiceV3 = errorServiceProvider.getObject();
        errorServiceV3.internal();
    }

    public void internal(){
        log.info("call internal");
    }
}

package com.example.springproxy.service.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
    내부 매서드에도 AOP 가 작동하도록 수정 3 - 코드 분리 (가장 추천하는 방법)
 */
@Service
@Slf4j
public class ErrorServiceV4_1 {

    public void internal(){
        log.info("call internal");
    }
}

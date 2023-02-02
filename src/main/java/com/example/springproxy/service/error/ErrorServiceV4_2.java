package com.example.springproxy.service.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ErrorServiceV4_2 {

    private final ErrorServiceV4_1 errorServiceV4_1;
    public void external() {
        log.info("call external");
        errorServiceV4_1.internal();
    }

}

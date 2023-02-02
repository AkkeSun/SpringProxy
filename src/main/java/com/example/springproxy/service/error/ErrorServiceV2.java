package com.example.springproxy.service.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
    내부 매서드에도 AOP 가 작동하도록 수정 1 - 셀프 주입 (순환 참조)
    스프링에서 AOP 가 적용된 대상을 의존관계 주입받으면 주입받은 대상은 실제 자신이 아니라 프록시가 된다.
    (Spring Boot 2.6 부터는 순환참조를 금지하도록 정책이 변경되었다. 그러니 이 방법을 사용하는건 비추!)
 */
@Slf4j
@Service
public class ErrorServiceV2 {

    private ErrorServiceV2 errorService2; // 셀프 주입

    @Autowired
    public void setErrorService2 (ErrorServiceV2 errorService2){
        this.errorService2 = errorService2;
    }

    public void external() {
        log.info("call external");
        errorService2.internal(); // 주입받은 객채(프록시) 의 함수를 사용
    }

    public void internal(){
        log.info("call internal");
    }
}

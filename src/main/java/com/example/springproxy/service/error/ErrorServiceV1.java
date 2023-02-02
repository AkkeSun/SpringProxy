package com.example.springproxy.service.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
    내부매서드는 AOP 작동하지 않는다
 */
@Service
@Slf4j
public class ErrorServiceV1 {

        // 해당 매서드에 AOP를 적용 후 실행하면 external() 에는 AOP가 적용되지만 external() 안에 있는 내부 매서드 internal() 에는 AOP가 적용되지 않는다.
        // 프록시를 생성할 수 없기 때문이다.
        public void external() {
                log.info("call external");
                internal();
        }

        // 해당 매서드에 AOP를 적용 후 실행하면 정상적으로 AOP가 작동한다.
        public void internal(){
                log.info("call internal");
        }

}

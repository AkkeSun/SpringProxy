package com.example.springproxy;

import com.example.springproxy.service.error.ErrorServiceV1;
import com.example.springproxy.service.error.ErrorServiceV2;
import com.example.springproxy.service.error.ErrorServiceV3;
import com.example.springproxy.service.error.ErrorServiceV4_2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

@SpringBootTest
public class ErrorServiceTest {

    @Autowired
    ErrorServiceV1 errorService;

    @Autowired
    ErrorServiceV2 errorServiceV2;

    @Autowired
    ErrorServiceV3 errorServiceV3;

    @Autowired
    ErrorServiceV4_2 errorServiceV4;


    @Test
    @Description("external 시작할 때에는 AOP가 작동하지만 내부매서드 실행할 떄에는 AOP가 작동하지 않는다")
    void externalTest(){
        errorService.external();
    }

    @Test
    @Description("AOP가 정상작동한다")
    void internalTest(){
        errorService.internal();
    }

    @Test
    @Description("내부 매서드 AOP 적용하기 1- 셀프 주입")
    void internalCallErrorSolution1(){
        errorServiceV2.external();
    }

    @Test
    @Description("내부 매서드 AOP 적용하기 2 - 지연 로딩")
    void internalCallErrorSolution2(){
        errorServiceV3.external();
    }

    @Test
    @Description("내부 매서드 AOP 적용하기 3 - 코드 분리")
    void internalCallErrorSolution3(){
        errorServiceV4.external();
    }
}

package com.example.springproxy;

import com.example.springproxy.repository.OrderRepository;
import com.example.springproxy.service.error.ErrorServiceV1;
import com.example.springproxy.service.OrderService;
import com.example.springproxy.service.error.ErrorServiceV2;
import com.example.springproxy.service.error.ErrorServiceV3;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

@Slf4j
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo(){
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success(){
        orderService.orderItem("test");
    }

    @Test
    void error(){
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
            .isInstanceOf(Exception.class);
    }
}

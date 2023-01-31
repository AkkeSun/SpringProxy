package com.example.springproxy.service;

import com.example.springproxy.aop.annotation.Retry;
import com.example.springproxy.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Retry(5)
    public void orderItem(String itemId){
        log.info("[orderItem] 실행");
        orderRepository.save(itemId);
    }
}

package com.hao.cloud.controller;

import com.hao.springcloud.entity.CommonResult;
import com.hao.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@Slf4j
@RestController
public class OrderController {
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/consumer/payment/create")
    public String create(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul", String.class);
    }
}

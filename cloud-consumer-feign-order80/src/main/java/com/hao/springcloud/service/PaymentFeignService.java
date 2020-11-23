package com.hao.springcloud.service;

import com.hao.springcloud.entity.CommonResult;
import com.hao.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment);
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id);
    @GetMapping("/payment/feign/timeout")
    public String testFeignTimeout();
}

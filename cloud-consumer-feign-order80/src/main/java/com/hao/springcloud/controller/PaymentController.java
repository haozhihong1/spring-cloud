package com.hao.springcloud.controller;

import com.hao.springcloud.entity.CommonResult;
import com.hao.springcloud.entity.Payment;
import com.hao.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Slf4j
@RestController
public class PaymentController {
    @Resource
    PaymentFeignService paymentFeignService;
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        log.info("插入数据{}",payment);
//        LinkedMultiValueMap map = new LinkedMultiValueMap<String,String>();
//        map.add("serial",payment.getSerial());
        return paymentFeignService.create(payment);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        return paymentFeignService.get(id);
    }
    @GetMapping("/consumer/payment/feign/timeout")
    public String testFeignTimeout(){
        return paymentFeignService.testFeignTimeout();
    }
}

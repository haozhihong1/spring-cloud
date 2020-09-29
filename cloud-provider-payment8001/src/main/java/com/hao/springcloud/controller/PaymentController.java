package com.hao.springcloud.controller;

import com.hao.springcloud.entity.CommonResult;
import com.hao.springcloud.entity.Payment;
import com.hao.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info("插入元素{}",payment);
        int result = paymentService.create(payment);
        log.info("*******插入结果{}",result);

        if (result > 0){
            return new CommonResult(200,"插入成功",result);
        } else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("*******查询结果{}",result);

        if (result != null){
            return new CommonResult(200,"查询成功",result);
        } else {
            return new CommonResult(444,"查询失败",null);
        }
    }
}

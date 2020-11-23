package com.hao.springcloud.controller;

import com.hao.springcloud.entity.CommonResult;
import com.hao.springcloud.entity.Payment;
import com.hao.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    PaymentService paymentService;
    @Resource
    DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String port;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info("插入元素{}",payment);
        int result = paymentService.create(payment);
        log.info("*******插入结果{}",result);

        if (result > 0){
            return new CommonResult(200,"插入成功{}" + port,result);
        } else {
            return new CommonResult(444,"插入数据库失败{}" + port,null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> create(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("*******查询结果{}",result);

        if (result != null){
            return new CommonResult(200,"查询成功{}" + port,result);
        } else {
            return new CommonResult(444,"查询失败" + port,null);
        }
    }
    @GetMapping("/payment/feign/timeout")
    public String testFeignTimeout(){
        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return port;
    }
    @GetMapping("/payment/discover")
    public Object discover(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("element****     " + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "    " + instance.getHost() + "    " + instance.getPort() + "    " + instance.getUri());
        }
        return this.discoveryClient;
    }
}

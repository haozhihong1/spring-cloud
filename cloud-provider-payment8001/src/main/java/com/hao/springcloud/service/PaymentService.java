package com.hao.springcloud.service;

import com.hao.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}

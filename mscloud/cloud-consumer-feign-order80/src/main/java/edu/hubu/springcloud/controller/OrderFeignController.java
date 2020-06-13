package edu.hubu.springcloud.controller;

import edu.hubu.springcloud.entities.CommonResult;
import edu.hubu.springcloud.entities.Payment;
import edu.hubu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by Sugar  2020/6/13 22:22
 */
@RestController
@Slf4j
public class OrderFeignController {


    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentByIdAndFeign(@PathVariable("id") Long id){
        log.info("open Fiegn:" + id);
        return paymentFeignService.getPaymentByIdAndFeign(id);
    }

    @GetMapping(value = "/consumer/payment/feign/get/{id}")
    public  String getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

}

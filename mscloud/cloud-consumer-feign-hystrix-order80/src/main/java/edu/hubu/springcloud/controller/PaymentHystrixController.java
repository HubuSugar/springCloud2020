package edu.hubu.springcloud.controller;

import edu.hubu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * created by Sugar  2020/6/21 0:03
 */
@RestController
@Slf4j
public class PaymentHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TIMEOUT(@PathVariable("id") Long id){
        return paymentHystrixService.paymentInfo_TIMEOUT(id);
    }
}

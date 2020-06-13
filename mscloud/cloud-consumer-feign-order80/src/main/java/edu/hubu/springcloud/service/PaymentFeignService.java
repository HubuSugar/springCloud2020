package edu.hubu.springcloud.service;

import edu.hubu.springcloud.entities.CommonResult;
import edu.hubu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by Sugar  2020/6/13 22:19
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentByIdAndFeign(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/get/{id}")
    String getPaymentById(@PathVariable("id")  Long id);
}

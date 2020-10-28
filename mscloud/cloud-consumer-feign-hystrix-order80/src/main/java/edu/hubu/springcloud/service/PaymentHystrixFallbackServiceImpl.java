package edu.hubu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * created by Sugar  2020/10/28 23:17
 */
@Component
public class PaymentHystrixFallbackServiceImpl implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Long id) {
        return "-----PaymentHystrixFallbackServiceImpl fall back-paymentInfo_OK ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TIMEOUT(Long id) {
        return "-----PaymentHystrixFallbackServiceImpl fall back-paymentInfo_TIMEOUT ,o(╥﹏╥)o";
    }
}

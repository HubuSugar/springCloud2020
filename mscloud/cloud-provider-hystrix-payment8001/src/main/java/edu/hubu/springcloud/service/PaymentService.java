package edu.hubu.springcloud.service;

import edu.hubu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by Sugar  2020/6/18 23:20
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentByid(@Param("id") Long id);

    String paymentInfo_Ok(@Param("id") Long id);

    String paymentInfo_TIMEOUT(@Param("id") Long id);

    String paymentCircuitBreaker(@Param("id") Integer id);

}
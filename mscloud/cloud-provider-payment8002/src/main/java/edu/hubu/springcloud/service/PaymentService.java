package edu.hubu.springcloud.service;

import edu.hubu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * created by Sugar  2020/5/30 22:06
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentByid(@Param("id") Long id);
}

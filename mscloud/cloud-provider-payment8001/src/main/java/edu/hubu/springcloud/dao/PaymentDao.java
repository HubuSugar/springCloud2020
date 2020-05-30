package edu.hubu.springcloud.dao;

import edu.hubu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * created by Sugar  2020/5/30 21:52
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentByid(@Param("id") Long id);
}

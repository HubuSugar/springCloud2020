package edu.hubu.springcloud.controller;

import edu.hubu.springcloud.entities.CommonResult;
import edu.hubu.springcloud.entities.Payment;
import edu.hubu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * created by Sugar  2020/5/30 22:11
 */
@Slf4j
@RestController
public class PaymentController {

    @Resource
    PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result + "O(∩_∩)O哈哈~");
        if(result > 0){
            return new CommonResult(200,"保存成功",result);
        }else{
            return new CommonResult(444,"保存失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentByid(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentByid(id);
        log.info("****查询支付单号：" +  payment);
        if(payment != null){
            return new CommonResult(200,"成功",payment);
        }else{
            return new CommonResult(444,"没有对应记录，ID:"+id,null);
        }
    }
}

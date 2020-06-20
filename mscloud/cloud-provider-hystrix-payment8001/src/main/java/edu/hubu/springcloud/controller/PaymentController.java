package edu.hubu.springcloud.controller;

import edu.hubu.springcloud.entities.CommonResult;
import edu.hubu.springcloud.entities.Payment;
import edu.hubu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * created by Sugar  2020/6/20 18:07
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String servePort;


    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result + "O(∩_∩)O哈哈~");
        if(result > 0){
            return new CommonResult(200,"保存成功,端口：" + servePort,result);
        }else{
            return new CommonResult(444,"保存失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentByid(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentByid(id);
        log.info("****查询支付单号：" +  payment);
        if(payment != null){
            return new CommonResult(200,"成功，端口：" + servePort,payment);
        }else{
            return new CommonResult(444,"没有对应记录，ID:"+id,null);
        }
    }


    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return servePort;
    }

    @GetMapping(value = "/payment/feign/get/{id}")
    public String getPaymentByIdAndFeign(@PathVariable("id")  Long id){
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servePort;
    }


    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        return paymentService.paymentInfo_Ok(id);
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentInfo_TIMEOUT(@PathVariable("id") Long id){
        return paymentService.paymentInfo_TIMEOUT(id);
    }
}

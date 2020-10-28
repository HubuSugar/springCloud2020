package edu.hubu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    /**
     * 客户端运行等待时间是1500ms
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TIMEOUTHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")})
    public String paymentInfo_TIMEOUT(@PathVariable("id") Long id){
        int age = 10 / 0;
        String result = paymentHystrixService.paymentInfo_TIMEOUT(id);
        return result;
    }

    private String paymentInfo_TIMEOUTHandler(Long id){
        return "我是消费者80，运行超时或者异常了" + "o(╥﹏╥)o" ;
    }
}

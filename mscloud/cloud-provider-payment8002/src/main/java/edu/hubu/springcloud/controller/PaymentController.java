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
 * created by Sugar  2020/5/30 22:11
 */
@Slf4j
@RestController
public class PaymentController {


    @Resource
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result + "O(∩_∩)O哈哈~");
        if(result > 0){
            return new CommonResult(200,"保存成功，端口：" + serverPort,result);
        }else{
            return new CommonResult(444,"保存失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentByid(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentByid(id);
        log.info("****查询支付单号：" +  payment);
        if(payment != null){
            return new CommonResult(200,"成功，端口："+serverPort,payment);
        }else{
            return new CommonResult(444,"没有对应记录，ID:"+id,null);
        }
    }

    /**
     * 服务发现
     * @return
     */
    @GetMapping(value="/payment/discovery")
    public Object discovery(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            log.info("******instance:"+instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getUri());
        }
        List<String> services = discoveryClient.getServices();
        for (String element:services) {
            log.info("******element:"+element);
        }

        return this.discoveryClient;

    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/get/{id}")
    public String getPaymentByIdAndFeign(@PathVariable("id")  Long id){
        try{
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}

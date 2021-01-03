package edu.hubu.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import edu.hubu.springcloud.entities.CommonResult;
import edu.hubu.springcloud.entities.Payment;
import edu.hubu.springcloudalibaba.handler.CustomHandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Value("${server-url.nacos-order-addr}")
    private String server_url;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/{id}")
    @SentinelResource(value = "fallback",fallback = "deal_fallback",blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable Long id){
        log.info("parameters --> " + id);
        CommonResult result = restTemplate.getForObject(server_url + "/paymentSQL/" + id, CommonResult.class, id);
        if (id == 4) {
//            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
            throw new RuntimeException ("IllegalArgumentException,非法参数异常....RunTimeException");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    //fallback
    public CommonResult<Payment> deal_fallback(@PathVariable Long id,Throwable ex){
        return new CommonResult<>(444,ex.getMessage() + id,null);
    }

    //本例是blockHandler
    public CommonResult blockHandler(@PathVariable  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult<>(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage() + id,payment);
    }

}

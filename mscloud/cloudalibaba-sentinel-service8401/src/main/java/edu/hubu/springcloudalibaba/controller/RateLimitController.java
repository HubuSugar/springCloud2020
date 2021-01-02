package edu.hubu.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import edu.hubu.springcloud.entities.CommonResult;
import edu.hubu.springcloud.entities.Payment;
import edu.hubu.springcloudalibaba.handler.CustomHandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value="byResource",blockHandler = "handlerException")
    public CommonResult<Payment> byReSource(){
        return new CommonResult(200, "成功", new Payment(1L, "202101020001"));
    }

    public CommonResult handlerException(BlockException ex){
        return new CommonResult(444,"服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value="byUrl",blockHandlerClass = CustomHandlerException.class,blockHandler = "customHandlerException2")
    public CommonResult<Payment> byUrl(){
        return new CommonResult(200 ,"成功-->byUrl", new Payment(2021L,"serial2021"));
    }


}

package edu.hubu.springcloudalibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import edu.hubu.springcloud.entities.CommonResult;
import edu.hubu.springcloud.entities.Payment;

public class CustomHandlerException {

    public static CommonResult<Payment> deal_fallback(BlockException ex){
        return new CommonResult<>(444,"出错啦",null);
    }
}

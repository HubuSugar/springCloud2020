package edu.hubu.springcloudalibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import edu.hubu.springcloud.entities.CommonResult;

public class CustomHandlerException {

    public static CommonResult customHandlerException1(BlockException ex){
        return new CommonResult(4444,"handlerException By customException1",null);
    }

    public static CommonResult customHandlerException2(BlockException ex){
        return new CommonResult(4444,"handlerException By customException2",null);
    }
}

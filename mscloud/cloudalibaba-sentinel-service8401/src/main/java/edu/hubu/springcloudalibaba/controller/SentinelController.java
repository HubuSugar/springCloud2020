package edu.hubu.springcloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class SentinelController {

    @Value("${server.port}")
    private String serverPort;

    /**
     * 阈值类型：QPS
     * 阈值：1
     * @return
     */
    @GetMapping("/testA")
    public String test(){
        return "testA -->" + serverPort;
    }

    /**
     * 阈值类型：QPS
     * 流控类型：关联，testA 关联 testB ，当testB达到流控时，限流testA
     * @return
     */
    @GetMapping("/testB")
    public String testB(){
        return "testB -->" + serverPort;
    }

    /**
     * 阈值类型：线程数
     * 阈值：1
     * @return
     */
    @GetMapping("/testC")
    public String testC(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testC --> " + serverPort;
    }

    /**
     * 阈值类型：QPS
     * 阈值：10
     * 流控效果：Warm up
     * @return
     */
    @GetMapping("/testD")
    public String testD(){
        return  "testD -->" + serverPort;
    }

    /**
     * 阈值类型:QPS
     * 阈值：1
     * 流控效果：排队等待
     * @return
     */
    @GetMapping("/testE")
    public String testE(){
        log.info("" + Thread.currentThread().getName() + "\t" + "...testE");
        return "testE -->" + serverPort;
    }

    /**---------------------------服务降级---------------------------------**/
    /**
     * RT ： 200ms
     * 时间窗口 ： 1s
     * @return
     */
    @GetMapping("/testF")
    public String testF(){
        try {
            TimeUnit.SECONDS.sleep(1);   //平均响应时间 1秒 一秒钟 >= 5 请求
            log.info("testF 测试RT");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testF --> " + serverPort;
    }

    /**
     * 异常比例测试
     * 异常比例 ： 0.2
     * 时间窗口：3s
     * @return
     */
    @GetMapping("/testG")
    public String testG(){

        log.info("testG 测试异常比例");
        int age = 10 / 0;
        return "testG --> " + serverPort;
    }

    /**
     * 测试异常数
     * 异常数：5
     * 时间窗口期：70s
     */
    @GetMapping("/testH")
    public String testH(){
        log.info("testH 测试数");
        int age = 10 / 0;
        return "testH --> " + serverPort;
    }

    /**
     * 测试热点key限流
     * 参数下标：1
     * QPS:1
     * 时间窗口：1s
     * 参数例外项：String类型
     *           疫情
     *           QPS --> 5
     * @param p1
     * @param p2
     * @return
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value="p1",required = false) String p1,@RequestParam(value="p2",required = false) String p2){
        log.info(p1 + "--" + p2);
        return "testHotKey --> " + serverPort;
    }

    public String deal_testHotKey(String p1, String p2, BlockException ex){
        return "deal_testHotKey --> " + p1 + "--" + p2;
    }

}

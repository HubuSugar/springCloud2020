package edu.hubu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * created by Sugar  2020/6/13 0:20
 */
@RestController
@Slf4j
public class OrderController {

    private static final String INVOKE_URL = "http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consumer/order/consul")
    public String consuleInfo(){
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul",String.class);
        String res = result + ":" + serverPort;
        return res;
    }


}

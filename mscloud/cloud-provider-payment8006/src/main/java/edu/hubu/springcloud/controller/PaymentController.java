package edu.hubu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * created by Sugar  2020/6/13 0:06
 */
@RestController
public class PaymentController {


    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/consul")
    public String consulInfo(){
        return "spring cloud:"+serverPort + UUID.randomUUID();
    }

}

package edu.hubu.springcloudalibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SentinelController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/testA")
    public String test(){
        return "-----------------testA" + serverPort;
    }

    @GetMapping("/testB")
    public String testB(){
        return "-----------------testB" + serverPort;
    }
}

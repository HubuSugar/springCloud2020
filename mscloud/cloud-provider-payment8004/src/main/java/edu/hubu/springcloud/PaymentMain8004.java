package edu.hubu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * created by Sugar  2020/6/12 0:23
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain8004 {


    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}

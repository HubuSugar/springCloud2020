package edu.hubu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * created by Sugar  2020/12/6 21:33
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamConsumerMain8803 {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerMain8803.class,args);
    }
}

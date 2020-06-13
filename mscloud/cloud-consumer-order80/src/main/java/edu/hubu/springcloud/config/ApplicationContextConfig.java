package edu.hubu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * created by Sugar  2020/5/31 0:17
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * 如果使用了ribbon自定义负载均衡算法 @LoadBalanced注解需要注释
     * @return
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

//applicationContext.xml <bean id="">

package edu.hubu.customLBRule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by Sugar  2020/6/13 16:42
 * 默认是轮询
 */
@Configuration
public class MySelfRule {

    /**
     * 负载均衡规则1：随机
     */
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }

}

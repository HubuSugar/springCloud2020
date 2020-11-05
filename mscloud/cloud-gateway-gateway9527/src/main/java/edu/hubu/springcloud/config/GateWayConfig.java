package edu.hubu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * created by Sugar  2020/11/1 23:31
 */
@Component
public class GateWayConfig {

    /**
     * 自定义的访问百度新闻的路由
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){

        /**
         * 经过测试会将path后面的路由，转发到uri这个地址下
         */
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("hubu-payment-route",
                r -> r.path("/guoji")
                        .uri("http://news.baidu.com")).build();
        return routes.build();
    }
}

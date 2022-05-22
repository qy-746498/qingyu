package io.qingyu.shop.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 配置类
 */
@Configuration
public class LoadBalanceConfig {
    @Bean
    //在服务端实现负载--
    @LoadBalanced
    //-----------
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

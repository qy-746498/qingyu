package io.qingyu.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 服务网关启动类
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayStarter {
    public static void main(String[] args) {
        SpringApplication.run(GatewayStarter.class,args);
    }
}

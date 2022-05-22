package io.qingyu.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 商品服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(value = { "io.qingyu.shop.product.mapper" })
@EnableTransactionManagement(proxyTargetClass = true)
public class ProductStarter {
    public static void main(String[] args){
        SpringApplication.run(ProductStarter.class, args);
    }
}

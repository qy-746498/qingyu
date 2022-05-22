package io.qingyu.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 启动用户服的类
 */
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(value = { "io.qingyu.shop.user.mapper" })
public class UserStarter {
    public static void main(String[] args){
        SpringApplication.run(UserStarter.class, args);
    }
}

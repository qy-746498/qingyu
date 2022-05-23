package io.qingyu.shop.order.feifn.factory;

import feign.hystrix.FallbackFactory;
import io.qingyu.shop.bean.User;
import io.qingyu.shop.order.feifn.UserRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 用户微服务容错Factory
 */
@Slf4j
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserRemoteService> {
    @Override
    public UserRemoteService create(Throwable throwable) {
        return new UserRemoteService() {
            @Override
            public User getUser(Long uid) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}

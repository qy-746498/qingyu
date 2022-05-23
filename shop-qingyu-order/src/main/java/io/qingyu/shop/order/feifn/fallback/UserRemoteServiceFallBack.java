package io.qingyu.shop.order.feifn.fallback;

import io.qingyu.shop.bean.User;
import io.qingyu.shop.order.feifn.UserRemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 用户服务容错类
 */
@Slf4j
@Component
public class UserRemoteServiceFallBack implements UserRemoteService {
    @Override
    public User getUser(Long uid) {
        User user = new User();
        user.setId(-1L);
        return user;
    }
}

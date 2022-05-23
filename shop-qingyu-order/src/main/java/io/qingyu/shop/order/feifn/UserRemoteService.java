package io.qingyu.shop.order.feifn;

import io.qingyu.shop.bean.User;
import io.qingyu.shop.order.feifn.factory.UserServiceFallBackFactory;
import io.qingyu.shop.order.feifn.fallback.UserRemoteServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 调用用户微服务的接口
 */
//只整合异常--------------------------------------------------------------------
//@FeignClient(value = "server-user",fallback = UserRemoteServiceFallBack.class)
//-----------------------------------------------------------------------------
//获取具体容错信息-----------------------------------------------------------------------
@FeignClient(value = "server-user",fallbackFactory = UserServiceFallBackFactory.class)
public interface UserRemoteService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}

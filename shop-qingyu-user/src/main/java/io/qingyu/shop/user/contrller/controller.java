package io.qingyu.shop.user.contrller;

import com.alibaba.fastjson.JSONObject;
import io.qingyu.shop.bean.User;
import io.qingyu.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 用户接口
 */
@Slf4j
@RestController
public class controller {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{uid}")
    public User getUser(@PathVariable("uid") Long uid){
        User user = userService.getUserById(uid);
        log.info("获取到的用户信息为：{}", JSONObject.toJSONString(user));
        return user;
    }
}

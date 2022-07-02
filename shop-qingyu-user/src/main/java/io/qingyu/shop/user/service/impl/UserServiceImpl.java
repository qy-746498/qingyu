package io.qingyu.shop.user.service.impl;

import io.qingyu.shop.bean.User;
import io.qingyu.shop.user.mapper.UserMapper;
import io.qingyu.shop.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 用户业务实现类
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
    @Async
    @Override
    public void asyncMethod() {
        log.info("执行了异步任务...");
    }
}

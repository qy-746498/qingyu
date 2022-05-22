package io.qingyu.shop.user.service.impl;

import io.qingyu.shop.bean.User;
import io.qingyu.shop.user.mapper.UserMapper;
import io.qingyu.shop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 用户业务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;
    @Override
    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }
}

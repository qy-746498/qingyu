package io.qingyu.shop.user.service;

import io.qingyu.shop.bean.User;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 用户业务接口
 */
public interface UserService {
    /**
     * 根据id获取用户信息
     */
    User getUserById(Long userId);
}

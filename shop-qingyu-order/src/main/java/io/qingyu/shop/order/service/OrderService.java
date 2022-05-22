package io.qingyu.shop.order.service;

import io.qingyu.shop.params.OrderParams;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 订单业务接口
 */
public interface OrderService {
    /**
     * 保存订单
     */
    void saveOrder(OrderParams orderParams);
}

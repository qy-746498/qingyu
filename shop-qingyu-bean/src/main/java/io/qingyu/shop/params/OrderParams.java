package io.qingyu.shop.params;

import lombok.Data;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 订单参数
 */
@Data
public class OrderParams {
    /**
     * 商品id
     */
    private Long productId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 购买的商品数量
     */
    private Integer count;

    public boolean isEmpty(){
        return (productId == null || productId <= 0) ||
                (userId == null || productId <= 0) ||
                (count == null || count <= 0);
    }
}

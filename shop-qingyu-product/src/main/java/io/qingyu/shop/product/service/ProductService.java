package io.qingyu.shop.product.service;

import io.qingyu.shop.bean.Product;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 商品Service接口
 */
public interface ProductService {
    /**
     * 根据商品id获取商品信息
     */
    Product getProductById(Long pid);


    /**
     * 扣减商品库存
     */
    int updateProductStockById(Integer count, Long id);
}

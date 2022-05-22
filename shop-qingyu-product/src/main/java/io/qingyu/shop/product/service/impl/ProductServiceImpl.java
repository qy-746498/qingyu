package io.qingyu.shop.product.service.impl;

import io.qingyu.shop.bean.Product;
import io.qingyu.shop.product.mapper.ProductMapper;
import io.qingyu.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 商品业务实现类
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product getProductById(Long pid) {
        return productMapper.selectById(pid);
    }

    @Override
    public int updateProductStockById(Integer count, Long id) {
        return productMapper.updateProductStockById(count, id);
    }
}

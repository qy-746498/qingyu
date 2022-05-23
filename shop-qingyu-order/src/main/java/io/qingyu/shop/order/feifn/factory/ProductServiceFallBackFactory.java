package io.qingyu.shop.order.feifn.factory;

import feign.hystrix.FallbackFactory;
import io.qingyu.shop.bean.Product;
import io.qingyu.shop.order.feifn.ProductRemoteService;
import io.qingyu.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 商品微服务容错Factory
 */
@Slf4j
@Component
public class ProductServiceFallBackFactory implements FallbackFactory<ProductRemoteService> {
    @Override
    public ProductRemoteService create(Throwable throwable) {
        return new ProductRemoteService() {
            @Override
            public Product getProduct(Long pid) {
                Product product = new Product();
                product.setId(-1L);
                return product;
            }

            @Override
            public Result<Integer> updateCount(Long pid, Integer count) {
                Result<Integer> result = new Result<>();
                result.setCode(1001);
                result.setCodeMsg("触发了容错逻辑");
                return result;
            }
        };
    }

}

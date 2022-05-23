package io.qingyu.shop.order.feifn;

import io.qingyu.shop.bean.Product;
import io.qingyu.shop.order.feifn.factory.ProductServiceFallBackFactory;
import io.qingyu.shop.order.feifn.fallback.ProductRemoteServiceFallBack;
import io.qingyu.shop.utils.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 调用商品微服务的接口
 */
//只整合异常--------------------------------------------------------------------------
//@FeignClient(value = "server-product",fallback = ProductRemoteServiceFallBack.class)
//----------------------------------------------------------------------------------
//获取具体容错信息--------------------------------------------------------------------------
@FeignClient(value = "server-product",fallbackFactory = ProductServiceFallBackFactory.class)
public interface ProductRemoteService {
    /**
     * 获取商品信息
     */
    @GetMapping(value = "/product/get/{pid}")
    Product getProduct(@PathVariable("pid") Long pid);

    /**
     * 更新库存数量
     */
    @GetMapping(value = "/product/update_count/{pid}/{count}")
    Result<Integer> updateCount(@PathVariable("pid") Long pid, @PathVariable("count") Integer count);
}

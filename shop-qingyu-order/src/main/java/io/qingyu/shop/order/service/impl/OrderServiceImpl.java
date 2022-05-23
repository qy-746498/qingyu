package io.qingyu.shop.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.qingyu.shop.bean.Order;
import io.qingyu.shop.bean.OrderItem;
import io.qingyu.shop.bean.Product;
import io.qingyu.shop.bean.User;
import io.qingyu.shop.order.feifn.ProductRemoteService;
import io.qingyu.shop.order.feifn.UserRemoteService;
import io.qingyu.shop.order.mapper.OrderItemMapper;
import io.qingyu.shop.order.mapper.OrderMapper;
import io.qingyu.shop.order.service.OrderService;
import io.qingyu.shop.params.OrderParams;
import io.qingyu.shop.utils.constants.HttpCode;
import io.qingyu.shop.utils.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description 订单服务
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
//    @Autowired
//    private RestTemplate restTemplate;
    //集成naco
    @Autowired
    private DiscoveryClient discoveryClient;
    private String userServer = "server-user";
    private String productServer = "server-product";

    //---------------------------------------------------------------
    //feign实现负载------------------------
    @Autowired
    private UserRemoteService userRemoteService;
    @Autowired
    private ProductRemoteService productRemoteService;
    //---------------------------------------
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(OrderParams orderParams) {
        //从Nacos服务中获取用户服务与商品服务的地址
//        String userUrl = this.getServiceUrl(userServer);
//        String productUrl = this.getServiceUrl(productServer);
        //----------------------------------------------------------
        if (orderParams.isEmpty()) {
            throw new RuntimeException("参数异常: " + JSONObject.toJSONString(orderParams));
        }

//        User user = restTemplate.getForObject("http://localhost:8060/user/get/" + orderParams.getUserId(), User.class);
        //从Nacos服务中获取用户服务与商品服务的地址--------------------------------------------------------------------------------
//        User user = restTemplate.getForObject("http://" + userUrl + "/user/get/" + orderParams.getUserId(), User.class);
        //------------------------------------------------------------------------------------------------------------------
        //服务端实现负载------------------------------------------------------------------------------------------------------------------
//        User user = restTemplate.getForObject("http://" + userServer + "/user/get/" + orderParams.getUserId(), User.class);
        //-----------------------------------------------------------------------------------------------------------------------------
        //feign实现负载-------------------------------------------------------------------------------------------------------------------
        User user = userRemoteService.getUser(orderParams.getUserId());
        //----------------------------------------------------------------------------------------------------------------------------
        if (user == null) {
            throw new RuntimeException("未获取到用户信息: " + JSONObject.toJSONString(orderParams));
        }
        //服务容错-----------------------------------------------------------------------------------
        if (user.getId() == -1){
            throw new RuntimeException("触发了用户微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        //---------------------------------------------------------------------------------------------------------------
//        Product product = restTemplate.getForObject("http://localhost:8070/product/get/" + orderParams.getProductId(), Product.class);
        //从Nacos服务中获取用户服务与商品服务的地址--------------------------------------------------------------------------------------------------
//        Product product = restTemplate.getForObject("http://" + productUrl + "/product/get/" + orderParams.getProductId(), Product.class);
        //----------------------------------------------------------------------------------------------------------------------------------
        //服务端实现负载-------------------------------------------------------------------------------------------------------------------------------
//        Product product = restTemplate.getForObject("http://" + productServer + "/product/get/" + orderParams.getProductId(), Product.class);
        //------------------------------------------------------------------------------------------------------------------------------------------
        //feign实现负载-------------------------------------------------------------------------------------------------------------------
        Product product = productRemoteService.getProduct(orderParams.getProductId());
        //---------------------------------------------------------------------------------------------------------------------------
        if (product == null) {
            throw new RuntimeException("未获取到商品信息: " + JSONObject.toJSONString(orderParams));
        }
        //服务容错-----------------------------------------------------------------------------------
        if (product.getId() == -1){
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        //-----------------------------------------------------------------------------------------------------------
        if (product.getProStock() < orderParams.getCount()) {
            throw new RuntimeException("商品库存不足: " + JSONObject.toJSONString(orderParams));
        }
        Order order = new Order();
        order.setAddress(user.getAddress());
        order.setPhone(user.getPhone());
        order.setUserId(user.getId());
        order.setUsername(user.getUsername());
        order.setTotalPrice(product.getProPrice().multiply(BigDecimal.valueOf(orderParams.getCount())));
        orderMapper.insert(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setNumber(orderParams.getCount());
        orderItem.setOrderId(order.getId());
        orderItem.setProId(product.getId());
        orderItem.setProName(product.getProName());
        orderItem.setProPrice(product.getProPrice());
        orderItemMapper.insert(orderItem);

//        Result<Integer> result = restTemplate.getForObject("http://localhost:8070/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        //从Nacos服务中获取用户服务与商品服务的地址-----------------------------------------------------------------------------------------------------------------------------------------------
//        Result<Integer> result = restTemplate.getForObject("http://" + productUrl + "/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //服务端实现负载----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//        Result<Integer> result = restTemplate.getForObject("http://" + productServer + "/product/update_count/" + orderParams.getProductId() + "/" + orderParams.getCount(), Result.class);
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //feign-------------------------------------------------------------------------------------------------------------
        Result<Integer> result = productRemoteService.updateCount(orderParams.getProductId(), orderParams.getCount());
        //-----------------------------------------------------------------------------------------------------------
        //服务容错-----------------------------------------------------------------------------------
        if (result.getCode() == 1001){
            throw new RuntimeException("触发了商品微服务的容错逻辑: " + JSONObject.toJSONString(orderParams));
        }
        //------------------------------------------------------------------------------------------------------------------
        if (result.getCode() != HttpCode.SUCCESS) {
            throw new RuntimeException("库存扣减失败");
        }
        log.info("库存扣减成功");
    }

    //Nacos获取Nacos中服务名称获取IP和端口号
//    private String getServiceUrl(String serviceName) {
//        //不实现负载------------------------------------------------------------------------
////        ServiceInstance serviceInstance = discoveryClient.getInstances(serviceName).get(0);
////        return serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        //--------------------------------------------------------------------------------
//        //实现负载在客户端实现------------------------------------------------------------------------
//        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
//        int index = new Random().nextInt(instances.size());
//        ServiceInstance serviceInstance = instances.get(index);
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        log.info("负载均衡后的服务地址为:{}", url);
//        return url;
//        //--------------------------------------------------------------------------
//    }
    //--------------------------------------------------------------------------------------
}

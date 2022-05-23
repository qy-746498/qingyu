package io.qingyu.shop.order.controller;

import com.alibaba.fastjson.JSONObject;
import io.qingyu.shop.order.service.OrderService;
import io.qingyu.shop.params.OrderParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qingYu
 * @version 1.0.0
 * @Description订单入口
 */
@Slf4j
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/submit_order")
    public String submitOrder(OrderParams orderParams){
        log.info("提交订单时传递的参数:{}", JSONObject.toJSONString(orderParams));
        orderService.saveOrder(orderParams);
        return "success";
    }
    //压测接口----------------------------------------
    @GetMapping(value = "/concurrent_request")
    public String concurrentRequest(){
        log.info("测试业务在高并发场景下是否存在问题");
        return "binghe";
    }
    @GetMapping(value = "/test_sentinel")
    public String testSentinel(){
        log.info("测试Sentinel");
        return "sentinel";
    }
    //---------------------------------------------
}

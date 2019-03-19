package com.easydao.earlybird.controller;

import com.easydao.earlybird.bean.OrderAircraft;
import com.easydao.earlybird.pay.PingxxProxy;
import com.easydao.earlybird.service.OrderAircraftService;
import com.easydao.earlybird.vendor.fuen.*;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pay")
public class PayController {
    private final static Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private OrderAircraftService orderAircraftService;

    @GetMapping(value = "/hello")
    public Object createInstance() {
        logger.error("hello {}", System.currentTimeMillis());

        OrderAircraft order = new OrderAircraft();
        order.setContact_id("eeeeeeeeee");
        order.setUser_id(11L);
        orderAircraftService.addOrderAircraft(order);
        System.out.println(order.getId());
        return "hello";
    }

    @PostMapping(value = "alipay")
    public Object alipay(@RequestBody PayParam param) throws InvalidRequestException, APIException, ChannelException, RateLimitException, APIConnectionException, AuthenticationException {
        int price = 100;
        Charge charge = PingxxProxy.alipayQR(param.orderNo(), price, "subject", "body");
        return charge.getCredential().get("alipay_qr");
    }

    @PostMapping(value = "wxpay")
    public Object wxpay(@RequestBody PayParam param) throws InvalidRequestException, APIException, ChannelException, RateLimitException, APIConnectionException, AuthenticationException {
        int price = 100;
        Charge charge = PingxxProxy.weixinQR(param.orderNo(), price, "subject", "body");
        return charge.getCredential().get("wx_pub_qr");
    }
}
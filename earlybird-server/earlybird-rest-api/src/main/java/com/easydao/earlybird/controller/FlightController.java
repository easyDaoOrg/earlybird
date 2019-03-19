package com.easydao.earlybird.controller;

import com.easydao.earlybird.bean.OrderAircraft;
import com.easydao.earlybird.pay.PingxxProxy;
import com.easydao.earlybird.service.OrderAircraftService;
import com.easydao.earlybird.util.Utils;
import com.easydao.earlybird.vendor.fuen.*;
import com.google.api.client.http.HttpResponseException;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("flight")
public class FlightController {
    private final static Logger logger = LoggerFactory.getLogger(FlightController.class);

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

    @PostMapping(value = "search")
    public Object search(@RequestBody SearchParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.search(param));
    }

    @PostMapping(value = "price")
    public Object price(@RequestBody PriceParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.price(param));
    }

    @PostMapping(value = "book")
    public Object book(@RequestBody BookParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
//        todo
//        param.cabinType();
        return Utils.toJson(proxy.book(param));
    }

    @PostMapping(value = "order")
    public Object book(@RequestBody OrderParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
//        todo
//        param.flightInfo().actFlightNum()
//        param.flightInfo().codeShare()
        return Utils.toJson(proxy.order(param));
    }

    @PostMapping(value = "pay")
    public Object pay(@RequestBody PayParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        proxy.payValidate(param);
        return Utils.toJson(proxy.pay(param));
    }

    @PostMapping(value = "order_detail")
    public Object orderDetail(@RequestBody OrderDetailParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.orderDetail(param));
    }

    @PostMapping(value = "refund_apply")
    public Object refundApply(@RequestBody RefundApplyParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.refundApply(param));
    }
}
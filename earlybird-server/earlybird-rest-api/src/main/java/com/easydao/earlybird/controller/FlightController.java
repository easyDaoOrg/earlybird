package com.easydao.earlybird.controller;

import com.easydao.earlybird.util.Utils;
import com.easydao.earlybird.vendor.fuen.*;
import com.google.api.client.http.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flight")
public class FlightController {
    private final static Logger logger = LoggerFactory.getLogger(FlightController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Object createInstance() {
        logger.error("hello {}", System.currentTimeMillis());
        return "hello";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public Object search(@RequestBody SearchParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.search(param));
    }

    @RequestMapping(value = "price", method = RequestMethod.POST)
    public Object price(@RequestBody PriceParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.price(param));
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public Object book(@RequestBody BookParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
//        todo
//        param.cabinType();
        return Utils.toJson(proxy.book(param));
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    public Object book(@RequestBody OrderParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
//        todo
//        param.flightInfo().actFlightNum()
//        param.flightInfo().codeShare()
        return Utils.toJson(proxy.order(param));
    }

    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public Object pay(@RequestBody PayParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        proxy.payValidate(param);
        return Utils.toJson(proxy.pay(param));
    }

    @RequestMapping(value = "order_detail", method = RequestMethod.POST)
    public Object orderDetail(@RequestBody OrderDetailParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.orderDetail(param));
    }

    @RequestMapping(value = "refund_apply", method = RequestMethod.POST)
    public Object refundApply(@RequestBody RefundApplyParam param) throws HttpResponseException {
        FuenProxy proxy = FuenProxy.apply();
        return Utils.toJson(proxy.refundApply(param));
    }
}
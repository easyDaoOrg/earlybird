package com.easydao.earlybird.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easydao.earlybird.bean.Contact;
import com.easydao.earlybird.bean.OrderAircraft;
import com.easydao.earlybird.service.ContactService;
import com.easydao.earlybird.service.OrderAircraftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("easydao/aircraft")
public class OrderAircraftController {

    private static final Logger logger = LoggerFactory.getLogger(OrderAircraftController.class);

    @Autowired
    private OrderAircraftService orderAircraftService;



    @PostMapping("addOrderAircraft")
    public String addOrderAircraft(@RequestBody OrderAircraft orderAircraft){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try {
            orderAircraftService.addOrderAircraft(orderAircraft);
            result.put("flag", true);
            result.put("orderAircraft", orderAircraft);
        }catch (Exception e){
                logger.error("添加飞机订单异常",e);
                e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }


    @PostMapping("updateOrderAircraft")
    public String updateOrderAircraft(@RequestBody OrderAircraft orderAircraft){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            orderAircraftService.updateOrderAircraft(orderAircraft);
        result.put("flag", true);
        }catch (Exception e){
            logger.error("更新飞机订单异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("getOrderAircraftList")
    public String getOrderAircraftList (long user_id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            List<OrderAircraft> orderAircraftList = orderAircraftService.getOrderAircraftList(user_id);
            result.put("flag", true);
            result.put("orderAircraftList", orderAircraftList);
        }catch (Exception e){
            logger.error("查询飞机订单异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("getOrderAircraft")
    public String getOrderAircraft (long id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            OrderAircraft orderAircraft = orderAircraftService.getOrderAircraft(id);
            result.put("flag", true);
            result.put("orderAircraft", orderAircraft);
        }catch (Exception e){
            logger.error("查询飞机订单异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

}

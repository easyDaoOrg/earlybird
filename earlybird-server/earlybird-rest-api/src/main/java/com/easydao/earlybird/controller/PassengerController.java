package com.easydao.earlybird.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.easydao.earlybird.bean.Certificate;
import com.easydao.earlybird.bean.Passenger;
import com.easydao.earlybird.bean.PassengerExtends;
import com.easydao.earlybird.bean.User;
import com.easydao.earlybird.service.CertificateService;
import com.easydao.earlybird.service.PassengerService;
import com.easydao.earlybird.service.UserService;
import com.easydao.earlybird.utils.ShiroToken;
import com.easydao.earlybird.utils.ShiroUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("easydao/passenger")
public class PassengerController {

    private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private CertificateService certificateService;


    @PostMapping("addPassenger")
    public String addPassenger(@RequestBody PassengerExtends passengerExtends){

        JSONObject result = new JSONObject();
        result.put("flag", false);
        try {
            passengerService.addPassenger(passengerExtends);
            result.put("flag", true);
        }catch (Exception e){
                logger.error("添加旅客异常",e);
                e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @DeleteMapping("deletePassenger/{id}")
    public String deletePassenger(@PathVariable("id") Long id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            passengerService.deletePassenger(id);
            result.put("flag", true);
        }catch (Exception e){
            logger.error("删除旅客异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @PostMapping("updatePassenger")
    public String updatePassenger(@RequestBody PassengerExtends passengerExtends){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
        passengerService.updatePassenger(passengerExtends);
        result.put("flag", true);
        }catch (Exception e){
            logger.error("更新旅客异常",e);
            e.printStackTrace();
            result.put("message",e.getMessage());
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("getPassengerList")
    public String getPassengerList (long user_id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            List<Passenger> passengerList = passengerService.getPassengerList(user_id);
            result.put("flag", true);
            result.put("list", passengerList);
        }catch (Exception e){
            logger.error("查询旅客异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("getPassenger")
    public String getPassenger (long id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            Passenger passenger = passengerService.getPassenger(id);
                List<Certificate> certificateList = certificateService.getCertificateList   (id);
            result.put("flag", true);
            result.put("passenger", passenger);
            result.put("certificateList",certificateList);
        }catch (Exception e){
            logger.error("查询旅客异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    public static void main(String[] args) {
        String str ="{user_id:1, passenger_name_cn:'aa', passenger_name_en:'wangyunlong', passenger_nationality:China, passenger_gender:man, passenger_birthday:1990-01-09}";
        Passenger passenger = JSON.parseObject(str,Passenger.class);
    }
}

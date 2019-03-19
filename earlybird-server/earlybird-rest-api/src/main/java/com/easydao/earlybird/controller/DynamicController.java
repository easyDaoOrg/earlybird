package com.easydao.earlybird.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easydao.earlybird.bean.Dynamic;
import com.easydao.earlybird.common.Constants;
import com.easydao.earlybird.service.DynamicService;
import com.easydao.earlybird.utils.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@CrossOrigin
@RestController
@RequestMapping("easydao/dynamic")
public class DynamicController {

    private static final Logger logger = LoggerFactory.getLogger(DynamicController.class);

    @Autowired
    private DynamicService dynamicService;

    @GetMapping("addDynamic")
    public String addDynamic(String user_account,String flag){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try {
            String user_dynamic_code = getRandomNum(4);
            //调用短信接口
            if("phone".equals(flag)){
                Integer templateId = Constants.TEMPLATE_ID;
                String[] params={user_dynamic_code};
                System.out.println(user_dynamic_code);
                SmsUtil.sendMess(user_account, templateId, params);
            }else if("email".equals(flag)){

            }
            Dynamic dynamic = new Dynamic();
            dynamic.setUser_account(user_account);
            dynamic.setUser_dynamic_code(user_dynamic_code);
            dynamicService.addDynamic(dynamic);
            result.put("flag", true);
        } catch (Exception e) {
            logger.error("动态码生产异常", e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("checkDynamic")
    public String checkDynamic(String user_account,String dynamic_code){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try {
            Dynamic dynamic = dynamicService.findDynamicByAccount(user_account);
            if(dynamic!=null&&dynamic_code.equals(dynamic.getUser_dynamic_code())){
                result.put("flag", true);
            }
        } catch (Exception e) {
            logger.error("获取动态码异常", e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }


    private String getRandomNum(int length) {
        String str="0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println();
    }

}

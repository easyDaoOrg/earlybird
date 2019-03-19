package com.easydao.earlybird.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.easydao.earlybird.bean.Certificate;
import com.easydao.earlybird.bean.Contact;
import com.easydao.earlybird.bean.Passenger;
import com.easydao.earlybird.service.CertificateService;
import com.easydao.earlybird.service.ContactService;
import com.easydao.earlybird.service.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("easydao/contact")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;



    @PostMapping("addCcontact")
    public String addCcontact(@RequestBody Contact contact){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try {
            contactService.addCcontact(contact);
            result.put("flag", true);
        }catch (Exception e){
                logger.error("添加联系人异常",e);
                e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @DeleteMapping("deleteContact/{id}")
    public String deleteContact(@PathVariable("id") Long id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            contactService.deleteContact(id);
            result.put("flag", true);
        }catch (Exception e){
            logger.error("删除联系人异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @PostMapping("updateContact")
    public String updateContact(@RequestBody Contact contact){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            contactService.updateContact(contact);
        result.put("flag", true);
        }catch (Exception e){
            logger.error("更新联系人异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("getContactList")
    public String getContactList (long user_id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try{
            List<Contact> contactList = contactService.getContactList(user_id);
            result.put("flag", true);
            result.put("contactList", contactList);
        }catch (Exception e){
            logger.error("查询联系人异常",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

}

package com.easydao.earlybird.utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.easydao.earlybird.common.Constants;

import javax.xml.ws.http.HTTPException;
/*
 *@author  czg
 *@date 2018/10/11 16:14
 *@description 腾讯云提供短信
 */
public class SmsUtil {

    public static void sendMess(String phoneNumbers, Integer templateId, String[] params){
        Boolean flag = false;
        // 短信应用SDK AppID
        int appid = Constants.TE_APPID;
        // 短信应用SDK AppKey
        String appkey = Constants.TE_APPKEY;
        // 短信签名
        String smsSign = Constants.TE_SIGNTEXT;
        try {
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers,  templateId, params, smsSign, "", "");

            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}

package com.easydao.earlybird.pay;

import com.google.common.io.Resources;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.*;
import com.pingplusplus.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class PingxxProxy {
    private final static Logger logger = LoggerFactory.getLogger(PingxxProxy.class);

    private final static String privateKey = "pingxx-private-key.pem";
    private final static String appId = "app_DSC0mHznTi9Ku1Wb";
    private final static String apiKey = "sk_test_C8Sy1KrLa9CCXT4eL4OqzDa5";

    /**
     * 支付渠道
     * https://www.pingxx.com/api/%E6%94%AF%E4%BB%98%E6%B8%A0%E9%81%93%E5%B1%9E%E6%80%A7%E5%80%BC
     */
    public enum PayChannel {
        alipay_qr,
        wx_pub_qr
    }

    /**
     * 获取支付宝支付二维码
     *
     * @param orderNo
     * @param price   以分为单位
     * @throws RateLimitException
     * @throws APIException
     * @throws ChannelException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws AuthenticationException
     */
    public static Charge alipayQR(String orderNo, int price, String subject, String body) throws RateLimitException, APIException, ChannelException, InvalidRequestException, APIConnectionException, AuthenticationException {
        Pingpp.apiKey = apiKey;
        Pingpp.privateKeyPath = Resources.getResource(privateKey).getPath();

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("order_no", orderNo);
        chargeParams.put("amount", price);
        Map<String, String> app = new HashMap<>();
        app.put("id", appId);
        chargeParams.put("app", app);
        chargeParams.put("channel", PayChannel.alipay_qr.toString());
        chargeParams.put("currency", "cny");
        chargeParams.put("client_ip", "127.0.0.1");
        chargeParams.put("subject", subject);
        chargeParams.put("body", body);
        Charge charge = Charge.create(chargeParams);

        logger.info("alipay orderNo:{}, price:{}, response:{}", orderNo, price, charge);
        return charge;
    }

    /**
     * 获取微信支付二维码
     *
     * @param orderNo
     * @param price
     * @param subject
     * @param body
     * @return
     * @throws RateLimitException
     * @throws APIException
     * @throws ChannelException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws AuthenticationException
     */
    public static Charge weixinQR(String orderNo, int price, String subject, String body) throws RateLimitException, APIException, ChannelException, InvalidRequestException, APIConnectionException, AuthenticationException {
        Pingpp.apiKey = apiKey;
        Pingpp.privateKeyPath = Resources.getResource(privateKey).getPath();

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("order_no", orderNo);
        chargeParams.put("amount", price);
        Map<String, String> app = new HashMap<>();
        app.put("id", appId);
        chargeParams.put("app", app);
        chargeParams.put("channel", PayChannel.wx_pub_qr.toString());
        chargeParams.put("currency", "cny");
        chargeParams.put("client_ip", "127.0.0.1");
        chargeParams.put("subject", subject);
        chargeParams.put("body", body);

        Map<String, String> extra = new HashMap<>();
        extra.put("product_id", "EDAO");
        chargeParams.put("extra", extra);
        Charge charge = Charge.create(chargeParams);

        logger.info("weixin orderNo:{}, price:{}, response:{}", orderNo, price, charge);
        return charge;
    }

    /**
     * @param chargeId
     * @return
     * @throws RateLimitException
     * @throws APIException
     * @throws ChannelException
     * @throws InvalidRequestException
     * @throws APIConnectionException
     * @throws AuthenticationException
     */
    public Charge isPaid(String chargeId) throws RateLimitException, APIException, ChannelException, InvalidRequestException, APIConnectionException, AuthenticationException {
        Pingpp.apiKey = apiKey;
        Pingpp.privateKeyPath = Resources.getResource(privateKey).getPath();
        Charge retrieve = Charge.retrieve(chargeId);
        logger.info("Charge {} is paid:{}", chargeId, retrieve.getPaid());
        return retrieve;
    }

    public static void main(String[] args) throws InvalidRequestException, APIException, ChannelException, RateLimitException, APIConnectionException, AuthenticationException {
        Charge charge = PingxxProxy.weixinQR("aadf", 123, "s", "b");
        System.out.println(charge);

    }
}


package com.rongdu.edu.modules.wxpay.model;

import com.rongdu.edu.common.utils.OrderNoUtil;
import com.rongdu.edu.modules.wxpay.util.TppPropertyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信调起支付model
 * @Auther: zhangp
 * @Date: 2019/4/23 11:07
 * @Description:
 */
public class WXArousePayModel extends WXBaseModel {

    /**
     * 应用ID
     */
    private String appid;

    /**
     * 商户号
     */
    private String partnerid;

    /**
     * 预支付交易会话ID
     */
    private String prepayid;
    /**
     * 扩展字段
     * 官方demo定义为 package ，package为java关键字，ex_package
     */
    private String ex_package;

    /**
     * 随机字符串
     */
    private String noncestr;

    private String timestamp;

    private String sign;

    public WXArousePayModel() {
        this.setAppid(TppPropertyUtil.getMessage("APP_ID"));
        this.setPartnerid(TppPropertyUtil.getMessage("MCH_ID"));
        this.setEx_package("Sign=WXPay");
        this.setNoncestr(OrderNoUtil.getSerialNumber());
        this.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(12);
        map.put("appid", appid);
        map.put("partnerid", partnerid);
        map.put("prepayid", prepayid);
        map.put("package", ex_package);
        map.put("noncestr", noncestr);
        map.put("timestamp", timestamp);
        return map;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getEx_package() {
        return ex_package;
    }

    public void setEx_package(String ex_package) {
        this.ex_package = ex_package;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public <T> T doPost(Object object, Class<T> tClass) {
        return null;
    }
}

package com.rongdu.edu.modules.wxpay.service;

import com.rongdu.edu.modules.wxpay.model.WXArousePayModel;
import com.rongdu.edu.modules.wxpay.model.WXOrderQueryModel;

import java.util.Map;

/**
 * @Auther: zhangp
 * @Date: 2019/4/22 14:12
 * @Description:
 */
public interface WXPayService {


    /**
     * 统一下单
     * @param params
     * @return
     */
    Object getWxPayOrder(Map<String, Object> params);


    /**
     * 获取微信调起支付订单接口
     * @param prepay_id
     * @return
     */
    WXArousePayModel getWXArousePay(String prepay_id);

    /**
     * 查询订单
     * @param params
     * @return
     */
    WXOrderQueryModel getOrderQuery(Map<String, Object> params);






}

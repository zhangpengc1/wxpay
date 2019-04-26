package com.rongdu.edu.modules.wxpay.service.impl;

import com.rongdu.edu.common.utils.OrderNoUtil;
import com.rongdu.edu.common.utils.StringUtil;
import com.rongdu.edu.modules.wxpay.model.WXArousePayModel;
import com.rongdu.edu.modules.wxpay.model.WXOrderModel;
import com.rongdu.edu.modules.wxpay.model.WXOrderQueryModel;
import com.rongdu.edu.modules.wxpay.service.WXPayService;
import com.rongdu.edu.modules.wxpay.util.TppPropertyUtil;
import com.rongdu.edu.modules.wxpay.util.WxMD5Util;
import com.rongdu.eloan.common.context.PayConstant;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auther: zhangp
 * @Date: 2019/4/22 14:12
 * @Description:
 */
@Service
public class WXPayServiceImpl implements WXPayService {

    private final String orderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder"; // 统一下单

    private final String queryUrl = "https://api.mch.weixin.qq.com/pay/orderquery"; // 统一下单

    @Override
    public Object getWxPayOrder(Map<String, Object> payMap) {

        WXOrderModel wxOrderModel = new WXOrderModel();
        wxOrderModel.setBody("body");
        wxOrderModel.setNotify_url(StringUtil.isNull(payMap.get("notify_url")));
        wxOrderModel.setTotal_fee(Integer.parseInt(payMap.get("total_fee").toString()));
        wxOrderModel.setOut_trade_no(StringUtil.isNull(payMap.get("outTradeNo")));
        wxOrderModel.setSpbill_create_ip(StringUtil.isNull(payMap.get("spbill_create_ip")));
        wxOrderModel.setNonce_str(OrderNoUtil.getSerialNumber());
        wxOrderModel.setTrade_type("APP");
        wxOrderModel.setSign(WxMD5Util.getSign(wxOrderModel.toMap()));
        wxOrderModel.setSubmitUrl(orderUrl);
        return wxOrderModel.doPost(wxOrderModel,WXOrderModel.class);
    }

    @Override
    public WXArousePayModel getWXArousePay(String prepayId) {
        WXArousePayModel wxArousePayModel = new WXArousePayModel();
        wxArousePayModel.setPrepayid(prepayId);
        wxArousePayModel.setSign(WxMD5Util.getSign(wxArousePayModel.toMap()));
        return wxArousePayModel;
    }

    /**
     * 查询订单
     * @param params
     * @return
     */
    @Override
    public WXOrderQueryModel getOrderQuery(Map<String, Object> params) {
        WXOrderQueryModel queryModel = new WXOrderQueryModel();
        queryModel.setTransaction_id(StringUtil.isNull(params.get("transaction_id")));
        queryModel.setOut_trade_no(StringUtil.isNull(params.get("out_trade_no")));
        queryModel.setSign(WxMD5Util.getSign(queryModel.toMap()));
        queryModel.setSubmitUrl(queryUrl);
        return queryModel.doPost(queryModel,WXOrderQueryModel.class);
    }
}

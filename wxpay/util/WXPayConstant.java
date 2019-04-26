package com.rongdu.edu.modules.wxpay.util;

/**
 * @Auther: zhangp
 * @Date: 2019/4/23 17:28
 * @Description:
 */
public class WXPayConstant {

    /**
     * 支付成功
     */
    public static final String WX_PAY_TRADE_STATE_SUCCESS = "SUCCESS";
    /**
     * 转入退款
     */
    public static final String WX_PAY_TRADE_STATE_REFUND = "REFUND";

    /**
     * 未支付
     */
    public static final String WX_PAY_TRADE_STATE_NOTPAY = "NOTPAY";

    /**
     * 已关闭
     */
    public static final String WX_PAY_TRADE_STATE_CLOSED = "CLOSED";

    /**
     * 已撤销
     */
    public static final String WX_PAY_TRADE_STATE_REVOKED = "REVOKED";

    /**
     * 支付失败
     */
    public static final String WX_PAY_TRADE_STATE_PAYERROR = "PAYERROR";
    /**
     * 用户支付中
     */
    public static final String WX_PAY_TRADE_STATE_USERPAYING = "USERPAYING";

    /**
     * 微信余额充值通知地址
     */
    public static final String WX_BALANCE_PAY_NOTIFY_URL = "/wx/balance/pay/notify.htm";

    /**
     * 微信充值游戏通知地址
     */
    public static final String WX_GAME_PAY_NOTIFY_URL = "/wx/game/pay/notify.htm";


    public static final String WX_PAY_SUCCESS_RESPONSE = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";


}

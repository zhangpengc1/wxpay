package com.rongdu.edu.modules.wxpay.model;

import com.rongdu.edu.modules.wxpay.util.TppPropertyUtil;
import com.rongdu.eloan.common.context.PayConstant;
import com.rongdu.eloan.common.utils.IpUtil;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信统一下单model
 * @Auther: zhangp
 * @Date: 2019/4/22 11:34
 * @Description:
 */
@XmlRootElement(name="xml")
public class WXOrderModel extends WXBaseModel {

    private static final long serialVersionUID = 6678709693675799296L;

    /**
     * 应用ID
     */

    private String appid;
    /**
     * 商户号
     */

    private String mch_id;
    /**
     * 随机字符串，不长于32位。
     */

    private String nonce_str;
    /**
     * 商品描述 商品描述交易字段格式根据不同的应用场景按照以下格式：
     * APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     */

    private String body;
    /**
     * 商户订单号
     */

    private String out_trade_no;

    /**
     * 总金额
     */

    private int total_fee;
    /**
     * 终端IP
     */

    private String spbill_create_ip;
    /**
     * 通知地址
     */

    private String notify_url;
    /**
     *
     */

    private String product_id;
    private String time_expire;
    /**
     * 签名 APP
     */

    private String sign;
    private String openid;


    // 返回参数
    /**
     * 返回状态码
     */

    private String return_code;

    /**
     * 返回信息
     */

    private String return_msg;


    // 以下字段在return_code为SUCCESS的时候有返回
    /**
     * 业务结果
     */

    private String result_code;
    /**
     * 错误代码
     */

    private String err_code;
    /**
     * 错误代码描述
     */
    private String err_code_des;


    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
    /**
     * 调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP
     */
    private String trade_type;

    /**
     * 预支付交易会话标识
     */
    private String prepay_id;


    public WXOrderModel() {
        this.setAppid(TppPropertyUtil.getMessage("APP_ID"));
        this.setMch_id(TppPropertyUtil.getMessage("MCH_ID"));
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>(12);
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("nonce_str", nonce_str);
        map.put("trade_type", trade_type);
        if(StringUtils.isNotBlank(time_expire)){
            map.put("time_expire", time_expire);
        }
        map.put("body", body);
        map.put("out_trade_no", out_trade_no);
        map.put("total_fee", total_fee);
        map.put("spbill_create_ip", spbill_create_ip);
        map.put("notify_url", notify_url);
        if(StringUtils.isNotBlank(product_id)){
            map.put("product_id", product_id);
        }
        if (StringUtils.isNotBlank(openid)) {
            map.put("openid", openid);
        }
        return map;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    @Override
    public String toString() {
        return "WxOrderModel{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", body='" + body + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", total_fee=" + total_fee +
                ", spbill_create_ip='" + spbill_create_ip + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", product_id='" + product_id + '\'' +
                ", time_expire='" + time_expire + '\'' +
                ", sign='" + sign + '\'' +
                ", openid='" + openid + '\'' +
                ", return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                '}';
    }
}

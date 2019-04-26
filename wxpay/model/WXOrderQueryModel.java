package com.rongdu.edu.modules.wxpay.model;

import com.rongdu.edu.common.utils.OrderNoUtil;
import com.rongdu.edu.common.utils.StringUtil;
import com.rongdu.edu.modules.wxpay.util.TppPropertyUtil;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询订单
 * @Auther: zhangp
 * @Date: 2019/4/23 14:19
 * @Description:
 */
@XmlRootElement(name="xml")
public class WXOrderQueryModel extends WXBaseModel{
    /**
     * 该接口提供所有微信支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。
     *  需要调用查询接口的情况：
     *  1.当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
     *  2.调用支付接口后，返回系统错误或未知交易状态情况；
     *  3.调用被扫支付API，返回USERPAYING的状态；
     *  4.调用关单或撤销接口API之前，需确认支付状态；
     */
    /**
     * 应用APPID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 微信订单号[优先使用]
     */
    private String transaction_id;
    /**
     * 商户订单号 商户系统内部的订单号，当没提供transaction_id时需要传这个
     */
    private String out_trade_no;
    /**
     * 随机字符串
     */
    private String nonce_str;

    private String sign;

    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;

    /**
     *   错误代码
     */
    private String err_code;

    /**
     *   错误代码描述
     */
    private String err_code_des;
    private String result_code;

    // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
    /**
     * 用户标识
     */
    private String openid;

    /**
     * 交易类型
     *
     */
    private String trade_type;

    /**
     * 交易状态
     *  SUCCESS—支付成功
     *  REFUND—转入退款
     *  NOTPAY—未支付
     *  CLOSED—已关闭
     *  REVOKED—已撤销（刷卡支付）
     *  USERPAYING--用户支付中
     *  PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String trade_state;

    /**
     * 付款银行
     */
    private String bank_type;

    /**
     * 总金额
     */
    private String total_fee;

    /**
     * 现金支付金额
     */
    private String cash_fee;

    /**
     * 支付完成时间
     */
    private String time_end;

    /**
     * 交易状态描述 对当前查询订单状态的描述和下一步操作的指引
     */
    private String trade_state_desc;


    public WXOrderQueryModel() {
        this.setAppid(TppPropertyUtil.getMessage("APP_ID"));
        this.setMch_id(TppPropertyUtil.getMessage("MCH_ID"));
        this.setNonce_str(OrderNoUtil.getSerialNumber());
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("appid",appid);
        map.put("mch_id",mch_id);
        if(StringUtil.isNotBlank(transaction_id)){
            map.put("transaction_id",transaction_id);
        }
        if(StringUtil.isNotBlank(out_trade_no)){
            map.put("out_trade_no",out_trade_no);
        }
        map.put("nonce_str",nonce_str);
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

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WXOrderQueryModel{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                '}';
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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
}

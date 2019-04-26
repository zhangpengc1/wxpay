package com.rongdu.edu.modules.wxpay.controller;

import com.rongdu.edu.modules.recharge.service.RechargeService;
import com.rongdu.edu.modules.wxpay.model.WXNotifyModel;
import com.rongdu.edu.modules.wxpay.service.impl.WXserviceImpl;
import com.rongdu.edu.modules.wxpay.util.JaxbUtils;
import com.rongdu.edu.modules.wxpay.util.WXPayConstant;
import com.rongdu.eloan.common.context.PayConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
public class WXNotifyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WXNotifyController.class);
    @Resource
    private RechargeService rechargeService;
    @Resource
    private WXserviceImpl wxPayService;


    @RequestMapping(value="/wx/balance/pay/notify.htm")
    public void balancePayNotifyUrl(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException {
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            LOGGER.info("/wx/balance/pay/notify 微信余额充值回调参数。。。"+resXml);
            //String result = wxPayService.payBack(resXml);
            WXNotifyModel notifyModel = (WXNotifyModel) JaxbUtils.xmlStringToObject(WXNotifyModel.class, resXml);
            // TODO 这里需要根据result做判断，因密钥等信息还未申请，这里先省略
            boolean validSign = notifyModel.validSign(resXml);
            System.out.println("validSign>>>>>>>>"+validSign);


            // 微信余额支付业务代码 TODO
            if(validSign && notifyModel.getReturn_code().equals(PayConstant.PAY_WX_RESULT_CODE_SUCCESS)){
                // 支付成功，进行业务处理
                rechargeService.doWXBalancePayNotify(notifyModel);
            }
            //return result;
        } catch (Exception e) {
           e.printStackTrace();
        }
        // 成功接收
        response.getWriter().write(WXPayConstant.WX_PAY_SUCCESS_RESPONSE);
    }



    @RequestMapping(value="/wx/game/pay/notify.htm")
    public void gamePayNotifyUrl(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException {
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            LOGGER.info("/wx/balance/pay/notify 微信游戏充值回调参数。。。"+resXml);
            //String result = wxPayService.payBack(resXml);
            WXNotifyModel notifyModel = (WXNotifyModel) JaxbUtils.xmlStringToObject(WXNotifyModel.class, resXml);
            // TODO 这里需要根据result做判断，因密钥等信息还未申请，这里线省略
            boolean validSign = notifyModel.validSign(resXml);
            System.out.println("validSign>>>>>>>>"+validSign);
            // 微信余额支付业务代码 TODO
            if(validSign && notifyModel.getReturn_code().equals(PayConstant.PAY_WX_RESULT_CODE_SUCCESS)){
                // 支付成功，进行业务处理
                rechargeService.doWXGamePayNotify(notifyModel);
            }
            //return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 成功接收
        response.getWriter().write(WXPayConstant.WX_PAY_SUCCESS_RESPONSE);
    }



























}

package com.rongdu.edu.modules.wxpay.model;


import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.rongdu.edu.modules.wxpay.util.HttpUtil;
import com.rongdu.edu.modules.wxpay.util.JaxbUtils;
import com.rongdu.edu.modules.wxpay.util.WXConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.texen.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: zhangp
 * @Date: 2019/4/22 15:39
 * @Description:
 */
public class WXBaseModel implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(WXBaseModel.class);
    // 不可提供set方法
    private static String submitUrl;


    public <T> T doPost(Object object, Class<T> tClass) {
        T rt = null;
        try {

            String requestParams = JaxbUtils.objectToXmlString(object);
            LOGGER.info("requestParams:" + requestParams);

            rt = (T) JaxbUtils.xmlStringToObject(tClass, HttpUtil.doPost(submitUrl, requestParams));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rt;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }


    public boolean validSign(String notifyData) {
        boolean flag = false;
        WXConfigUtil config = null;
        try {
            //config = new WXConfigUtil("APIv3");
            config = new WXConfigUtil();
            WXPay wxpay = new WXPay(config);
            Map<String, String>  notifyMap = WXPayUtil.xmlToMap(notifyData);         // 调用官方SDK转换成map类型数据
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {//验证签名是否有效，有效则进一步处理
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return flag;
    }

}

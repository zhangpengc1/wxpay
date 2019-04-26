package com.rongdu.edu.modules.wxpay.service;

import java.util.Map;
@Deprecated
public interface WXservice {


    Map<String, String> dounifiedOrder(Map<String,Object> param);




    String payBack(String notifyData);
}

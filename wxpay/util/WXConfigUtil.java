package com.rongdu.edu.modules.wxpay.util;


import com.github.wxpay.sdk.WXPayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


public class WXConfigUtil implements WXPayConfig {

    private static final Logger logger = LoggerFactory.getLogger(WXConfigUtil.class);

    private byte[] certData;
    public  String APP_ID = TppPropertyUtil.getMessage("APP_ID");
    public  String KEY = TppPropertyUtil.getMessage("KEY");
    public  String MCH_ID = TppPropertyUtil.getMessage("MCH_ID");

    public WXConfigUtil(){
//        //String certPath = "证书地址";//从微信商户平台下载的安全证书存放的路径
//        String certPath = "";
//        InputStream certStream = null;
//
//        try {
//            File file = new File(certPath);
//            certStream = new FileInputStream(file);
//            this.certData = new byte[(int) file.length()];
//            certStream.read(this.certData);
//            certStream.close();
//        }catch (FileNotFoundException e){
//            logger.error("日志 =============》：配置文件找不到");
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public WXConfigUtil(String flag){
       if(flag.equals("APIv3")){
            this.KEY = TppPropertyUtil.getMessage("APIv3KEY");
       }else {
            this.KEY = TppPropertyUtil.getMessage("KEY");
       }
    }


    @Override
    public String getAppID() {
        return APP_ID;
    }

    //parnerid，商户号
    @Override
    public String getMchID() {
        return MCH_ID;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}


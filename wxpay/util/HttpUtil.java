package com.rongdu.edu.modules.wxpay.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类-Http请求
 * 
 * @author xx
 * @version 2.0
 * @since 2014年1月28日
 */
public class HttpUtil {
	
	
private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
private static String charset = "UTF-8";
	
	/**
	 * HttpClient连接超时时间(毫秒)
	 */
	public static final int UFX_CONN_TIME = 1000*60;
	
	/**
	 * HttpClient数据传输超时时间(毫秒)
	 */
	public static final int UFX_DATA_TRANS = 1000*60;
	
	/**
	 * 根据请求参数生成List<BasicNameValuePair>
	 * @param params
	 * @return
	 */
	private static List<BasicNameValuePair> wrapParam2(String[][] params) {
		List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();
        for (int i = 0; i < params.length; i++) {
        	data.add(new BasicNameValuePair(params[i][0], params[i][1]));
        }
        return data;
	}
	
	/**
	 * HTTP POST请求
	 * @param clientURL 请求地址
	 * @param params 参数数组
	 * @return
	 */
	public static String postClient(String clientURL,String[][] params) {
		String retMsg = "";
		HttpPost post = new HttpPost(clientURL);
		CloseableHttpClient client = HttpClients.createDefault();
		try {
			//参数封装
			List<BasicNameValuePair> paramsList = wrapParam2(params);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramsList, charset);
			
			//设置请求和传输时长
			Builder builder = RequestConfig.custom();

			builder.setSocketTimeout(UFX_DATA_TRANS);
			builder.setConnectTimeout(UFX_CONN_TIME);
			
			RequestConfig config = builder.build();
			
			post.setEntity(entity);
			post.setConfig(config);
			//发起请求
			CloseableHttpResponse response = client.execute(post);
			HttpEntity httpEntity = response.getEntity();
			String str = "";
			if (httpEntity != null) {
				str = EntityUtils.toString(httpEntity, charset);
			}
			System.out.println("返回消息-================="+str);
			return str;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if(client!=null){
					client.close();
				}
				if(post!=null){
					post.releaseConnection();
				}
			} catch (IOException e) {
				logger.error("关闭post请求流异常",e);
			}
		}
		return retMsg;
	}
	
	/**
	 * 发起http请求，获取响应结果
	 * 
	 * @param pageURL
	 * @return
	 */
	public static String getHttpResponse(String pageURL) {
		String pageContent = "";
		BufferedReader in = null;
		InputStreamReader isr = null;
		InputStream is = null;
		HttpURLConnection huc = null;
		try {
			URL url = new URL(pageURL);
			huc = (HttpURLConnection) url.openConnection();
			is = huc.getInputStream();
			isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			String line = null;
			while (((line = in.readLine()) != null)) {
				if (line.length() == 0)
					continue;
				pageContent += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
				if (isr != null)
					isr.close();
				if (in != null)
					in.close();
				if (huc != null)
					huc.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pageContent;
	}

	public static <T> T getJson(String url, Class<T> clazz) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpGet.addHeader("Connection", "Keep-Alive");
		httpGet.addHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
		httpGet.addHeader("Cookie", "");

		try {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpGet);

			if (response == null) {
				return null;
			}
			String result = EntityUtils.toString(response.getEntity());
			return JSON.parseObject(result.toString().trim(), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 发送 POST 请求（HTTP），JSON形式
	 * 
	 * @param apiUrl
	 * @param json
	 *            json对象
	 * @return
	 */
	public static String doPost(String apiUrl, Object json) {
		String httpStr = null;
		CloseableHttpResponse response = null;
		String rtnMsg = null;
		try {
		CloseableHttpClient httpClient = HttpClients.createDefault();
	
		HttpPost httpPost = new HttpPost(apiUrl);
		// 设置请求和传输超时时间3分钟
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(1000*60*3)
				.setConnectTimeout(1000*60*3).build();
		httpPost.setConfig(requestConfig);
		
		
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			//请求失败自动重试3次
			response = sendRequest(httpClient, httpPost, 3);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
//			if (e instanceof SocketTimeoutException || e instanceof ConnectTimeoutException) {
//				rtnMsg = "{\"code\":\"400\",\"msg\":\"超时\"}";
//				System.out.println(rtnMsg);
//			}
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (rtnMsg != null)
				httpStr = rtnMsg;
		}
		return httpStr;
	}
	
	/**
	 * 请求失败自动重试机制
	 * @description
	 * @param client
	 * @param request 
	 * @param count 重试次数
	 * @author wulb
	 * @return void
	 * @throws Exception 
	 * @since  1.0.0
	 */
	private static CloseableHttpResponse sendRequest(CloseableHttpClient client, HttpPost request, int count) throws Exception {
		CloseableHttpResponse response = null;
		for (int i = 0; i < count; i++) {
			try {
				response = client.execute(request);
				break;
			} catch (Exception e) {
				if(i==count-1){
					throw new Exception();
				}					
			}
		}
		return response;
	}

}

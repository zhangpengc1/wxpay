package com.rongdu.edu.modules.wxpay.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 工具类-消息文件
 */
public final class TppPropertyUtil {
	 private static final int DEFAULT_BUFFER_SIZE = 8192;
	/** 日志 */
	private static final Logger LOGGER = Logger.getLogger(TppPropertyUtil.class);

	/** 资源属性 */
	private static Properties properties;

	/**
	 * 私有构造方法
	 */
	private TppPropertyUtil() {
	}

	static {
		properties = new Properties();
		try {
			// 读取配置文件
			properties.load(TppPropertyUtil.class.getClassLoader().getResourceAsStream("config/WXpay-Config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.error("读取配置文件出错，请确认cbhbTpp.properties文件已经放到src目录下。");
		}
	}
	/**
	 * 获取配置信息
	 * 
	 * @param key 键
	 * @return 配置信息
	 */
	public static String getMessage(String key) {
		String value = null;
		value = properties.getProperty(key);
		if (null == value || "".equals(value)) {
			LOGGER.warn("【" + key + "】值为空");
		}
		return value;
	}

	/**
	 * 获取配置信息
	 * 
	 * @param key 键
	 * @param param 参数
	 * @return 配置信息
	 */
	public static String getMessage(String key, Object[] param) {
		String value = getMessage(key);
		return MessageFormat.format(value, param);
	}
//	/**
//	 *  获取公钥
//	 * @param algorithm
//	 * @param ins
//	 * @return
//	 * @throws Exception
//	 */
//    public static PublicKey getPublicKeyFromX509(String algorithm, InputStream ins) throws Exception {
//        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
//        StringWriter writer = new StringWriter();
//        io(new InputStreamReader(ins), writer, -1);
//        byte[] encodedKey = Base64Utils.decode(writer.toString());
//       return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
//    }
//    /**
//     * 获取私钥
//     * @param algorithm
//     * @param ins
//     * @return
//     * @throws Exception
//     */
//	public static PrivateKey getPrivateKeyFromPKCS8(String algorithm,InputStream ins) throws Exception {
//		if (ins == null || StringUtils.isBlank(algorithm)) {
//			return null;
//		}
//
//		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
//         byte[] encodedKey = readText(ins).getBytes();
//		 encodedKey = Base64.decodeBase64(encodedKey);
//		 return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
//
//	}
	private static String readText(InputStream ins) throws IOException {
		Reader reader = new InputStreamReader(ins);
		StringWriter writer = new StringWriter();
		io(reader, writer, -1);
		return writer.toString();
	}
	private static void io(Reader in, Writer out, int bufferSize)
			throws IOException {
		if (bufferSize == -1) {
			bufferSize = DEFAULT_BUFFER_SIZE >> 1;
		}
		char[] buffer = new char[bufferSize];
		int amount;

		while ((amount = in.read(buffer)) >= 0) {
			out.write(buffer, 0, amount);
		}
	}
	public static void main(String[] args) {
		System.out.println(TppPropertyUtil.getMessage("cbhb_fileser_type"));
		
	}
}

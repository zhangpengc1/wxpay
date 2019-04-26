package com.rongdu.edu.modules.wxpay.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

/**
 * 对象与xml之间相互转换的工具类
 * @author zhangp
 */
public class JaxbUtils {
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * 指定对象和编码方式将对象解析为xml字符串
     *
     * @param obj
     * @param encoding
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    public static String objectToXmlString(Object obj, String encoding) throws JAXBException, IOException {
        return objectToXmlString(obj, false, false, encoding);
    }
    /**
     * 按照默认的编码方式将对象解析为xml字符串
     *
     * @param obj
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    public static String objectToXmlString(Object obj) throws JAXBException, IOException {
        return objectToXmlString(obj, null);
    }

    /**
     *
     * @param obj
     * @param isFormat
     *            是否格式化
     * @param cancelXMLHead
     *            是否省略xml文件头
     * @param encoding
     *            编码方式， 默认为“gb312”
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public static String objectToXmlString(Object obj, boolean isFormat,boolean cancelXMLHead, String encoding) throws JAXBException, IOException {
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.setListener(new MarshallerListener());
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);
        m.setProperty(Marshaller.JAXB_FRAGMENT, cancelXMLHead);
        //m.setProperty(Marshaller.JAXB_ENCODING, encoding);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        m.marshal(obj, out);
        String xmlString = new String(out.toByteArray());
        out.flush();
        out.close();
        return xmlString.replace("standalone=\"yes\"", "");
    }

    /**
     * 将xml字符串解析为对象
     * @param obj
     * @param xmlString
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Object xmlStringToObject(Class<?> cl, String xmlString) throws JAXBException, IOException {
        Object object = null;
        StringReader reader = new StringReader(xmlString);
        JAXBContext context = JAXBContext.newInstance(cl);
        Unmarshaller u = context.createUnmarshaller();
        object = (Object) u.unmarshal(reader);
        reader.close();
        return object;
    }

}

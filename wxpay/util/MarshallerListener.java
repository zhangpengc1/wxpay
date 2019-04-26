package com.rongdu.edu.modules.wxpay.util;
import org.apache.commons.lang.StringUtils;

import javax.xml.bind.Marshaller;
import java.lang.reflect.Field;

/**
 * 该监听器主要用来解决jaxb无法渲染字段为null的属性.
 * <p>
 *     在将Java类转换为xml片段时,默认jaxb会过滤属性值为null的属性,通过该监听器的{@link #beforeMarshal(Object)}方法，在渲染前
 *     通过将属性赋值为空字符串来达到在生成的xml片段中包含该属性的功能.
 * </p>
 * <p>
 *     <strong>注意:</strong>默认处理{@link String}类型的字段.
 * </p>
 */
public class MarshallerListener extends Marshaller.Listener {
    @Override
    public void beforeMarshal(Object source) {
        super.beforeMarshal(source);
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field f : fields) {
             f.setAccessible(true);
             try {
                    if (f.getType() == String.class && f.get(source) == null) {
                        f.set(source, StringUtils.EMPTY);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }
}

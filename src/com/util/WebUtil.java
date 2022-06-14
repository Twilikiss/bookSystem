package com.util;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @Description 将map对象注入到对应的bean中
 * @ClassName WebUtil
 * @author   mlp52
 * @createTime  2022/3/13 21:41
 * @return
 * @Param
 */
public class WebUtil {
    public static <T>T copyParamTo(Map value, T bean) throws Exception {
        BeanUtils.populate(bean,value);
        return bean;
    }
}

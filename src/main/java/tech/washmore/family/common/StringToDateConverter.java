package tech.washmore.family.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary 日期转换器, 用于处理请求中的日期字符串转化为日期对象
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@Configuration
public class StringToDateConverter implements Converter<String, Date> {
    private static List<SimpleDateFormat> formators;

    static {
        formators = new ArrayList<>();
        formators.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        formators.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    @Override
    public Date convert(String source) {
        for (SimpleDateFormat format : formators) {
            try {
                return format.parse(source);
            } catch (Exception e) {
            }
        }
        return new Date();
    }

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void initEditableValidation() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
                .getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer
                    .getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }

    }

}
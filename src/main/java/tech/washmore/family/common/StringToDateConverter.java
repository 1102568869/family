package tech.washmore.family.common;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 字符串转日期的转换器
 *
 * @author byshome
 * @version $Id: StringToDateConverter.java, v 0.1 2015年9月24日 下午7:19:41 byshome Exp $
 */
@Configuration
public class StringToDateConverter implements Converter<String, Date> {
    private static List<SimpleDateFormat> formators;

    static {
        formators = new ArrayList<>();
        formators.add(new SimpleDateFormat("yyyy/MM/dd"));
        formators.add(new SimpleDateFormat("yyyy-MM-dd"));
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
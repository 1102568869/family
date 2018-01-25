package tech.washmore.family.common;

import com.alibaba.fastjson.serializer.DoubleSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.Arrays;

/**
 * @author Washmore
 * @version V1.0
 * @summary 配置FastJsonHttpMessageConverter作为controller的默认json解析器(替代jackson)
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/16
 */
@Configuration
@ConditionalOnClass({FastJsonHttpMessageConverter.class}) //1
@ConditionalOnProperty(//2
        name = {"spring.http.converters.preferred-json-mapper"},
        havingValue = "fastjson",
        matchIfMissing = true
)
public class FastJson2HttpMessageConverterConfiguration {

    @Bean
    @ConditionalOnMissingBean({FastJsonHttpMessageConverter.class})//3
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();//4
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteClassName,
                SerializerFeature.WriteMapNullValue
        );
        //null给到前台为空字符串""
        ValueFilter valueFilter = new ValueFilter() {//5
            //o 是class
            //s 是key值
            //o1 是value值
            public Object process(Object o, String s, Object o1) {
                if (null == o1) {
                    o1 = "";
                }
                return o1;
            }
        };
        fastJsonConfig.setSerializeFilters(valueFilter);
        SerializeConfig serializeConfig = SerializeConfig.getGlobalInstance();
        //指定double的格式,防止被科学计数法
        serializeConfig.put(Double.class, new DoubleSerializer("#.####"));
        fastJsonConfig.setSerializeConfig(serializeConfig);

        converter.setFastJsonConfig(fastJsonConfig);
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
        return converter;
    }
}
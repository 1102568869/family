package tech.washmore.family.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.CacheControl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tech.washmore.family.common.interceptor.LoginInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @author Washmore
 * @version V1.0
 * @summary MVC相关配置
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@Configuration
@ConditionalOnWebApplication
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * @summary 静态资源处理器
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CacheControl cacheControl = CacheControl.maxAge(8, TimeUnit.HOURS);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/pages/").setCacheControl(cacheControl);
    }

    /**
     * @summary 拦截器管理
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "", "/static/**", "/login", "/login4Wx");
        super.addInterceptors(registry);
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}

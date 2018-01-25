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

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8088");
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedOrigin("http://127.0.0.1:8080");

        corsConfiguration.addAllowedOrigin("*");

        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }


    /**
     * @summary 支持跨域请求的配置,
     * NOTICE:注意此配置仅适用于开发环境前端调试!!!请确保通过某种手段在生产环境强制覆盖掉spring.profiles.active以保证使此配置失效
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @Bean
    @Profile("development")
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

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

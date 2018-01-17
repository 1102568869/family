package tech.washmore.family.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tech.washmore.family.common.interceptor.LoginInterceptor;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaojian
 * @version v1
 * @since 2016-11-30
 */
@Configuration
@ConditionalOnWebApplication
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://*.washmore.tech:*", "http://*.washmoretech.com:*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CacheControl cacheControl = CacheControl.maxAge(8, TimeUnit.HOURS);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/pages/");//.setCacheControl(cacheControl);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      //  registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/", "", "/login","/toLogin");
        super.addInterceptors(registry);
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}

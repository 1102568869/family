package tech.washmore.family.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tech.washmore.family.common.SpringBootVFS;

import java.io.IOException;

/**
 * @author Washmore
 * @version V1.0
 * @summary 数据源配置, tomcat-pool作为springboot的内置数据连接池之一,可以被自动装配(详见DataSourceBuilder类),仅需在此配置mybatis的相关内容
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private DataSource dataSource;

    /**
     * @return SqlSessionFactoryBean
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean setSqlSessionFactoryBean() {
        DataSource dataSource = this.dataSource;
        //      DataSource dataSource = context.getBean(DataSource.class);
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        factory.setVfs(SpringBootVFS.class);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setConfigLocation(resolver.getResource("config/mybatis-config.xml"));
        try {
            factory.setMapperLocations(resolver.getResources("mappers/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return factory;
    }
}

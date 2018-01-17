package tech.washmore.family.common;

import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary 解决在如下情况中无法扫描到alias的BUG
 * 使用Spring Boot，并使用Spring Boot的Maven插件打包
 * 使用MyBatis（目前最新的 3.3.1 版本仍有这个问题）
 * 将Domain配置在单独的Jar包中（例如Maven多模块）
 * 使用 SqlSessionFactoryBean.setTypeAliasesPackage 指定包扫描Domain
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/17
 */
public class SpringBootVFS extends VFS {

    private final ResourcePatternResolver resourceResolver;

    public SpringBootVFS() {
        this.resourceResolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    protected List<String> list(URL url, String path) throws IOException {
        Resource[] resources = resourceResolver.getResources("classpath*:" + path + "/**/*.class");
        List<String> resourcePaths = new ArrayList<String>();
        for (Resource resource : resources) {
            resourcePaths.add(preserveSubpackageName(resource.getURI(), path));
        }
        return resourcePaths;
    }

    private static String preserveSubpackageName(final URI uri, final String rootPath) {
        final String uriStr = uri.toString();
        final int start = uriStr.indexOf(rootPath);
        return uriStr.substring(start);
    }

}

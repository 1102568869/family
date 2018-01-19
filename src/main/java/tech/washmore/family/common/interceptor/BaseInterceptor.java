package tech.washmore.family.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tech.washmore.family.common.Constants;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseInterceptor {
    protected void forbidden(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(Charsets.UTF_8.toString());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");

        response.getWriter().print(JSON.toJSONString(ImmutableMap.of("message", "未授权用户")));
    }

    protected void unauthorized(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(Charsets.UTF_8.toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");

        response.getWriter().print(JSON.toJSONString(ImmutableMap.of("message", "未登录用户")));
    }

    protected void errorRequestParamOfLoginUserCode(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding(Charsets.UTF_8.toString());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");

        response.getWriter().print(JSON.toJSONString(ImmutableMap.of("message", String.format("不允许使用%s或%s关键字作为requestAttribute!", Constants.REQUEST_MEMBER_ACCOUNT, Constants.REQUEST_MEMBER_ID))));
    }
}

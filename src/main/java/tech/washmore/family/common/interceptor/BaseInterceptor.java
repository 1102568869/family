package tech.washmore.family.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tech.washmore.family.common.Constants;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Washmore
 * @version V1.0
 * @summary 拦截器基类, 用于向response中写入各种错误信息
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/17
 */
public abstract class BaseInterceptor {

    private void writeMessege(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setCharacterEncoding(Charsets.UTF_8.toString());
        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8088");
        response.getWriter().print(JSON.toJSONString(ImmutableMap.of("message", message)));
    }

    protected void forbidden(HttpServletResponse response) throws Exception {
        this.writeMessege(response, HttpStatus.FORBIDDEN.value(), "未授权用户");
    }

    protected void unauthorized(HttpServletResponse response) throws Exception {
        this.writeMessege(response, HttpStatus.UNAUTHORIZED.value(), "未登录用户");
    }

    protected void errorRequestParamOfLoginUserCode(HttpServletResponse response) throws Exception {
        this.writeMessege(response, HttpStatus.UNAUTHORIZED.value(), String.format("不允许使用%s或%s关键字作为requestAttribute!", Constants.REQUEST_MEMBER_ACCOUNT, Constants.REQUEST_MEMBER_ID));
    }
}

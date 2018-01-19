package tech.washmore.family.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.washmore.family.utils.SessionUtil;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class BaseExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(BaseExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public void handle(Exception e) {
        try {
            logger.error(String.format("BaseExceptionHandler处理的异常:%s", e.getMessage()), e);
            HttpServletResponse response = SessionUtil.getHttpResponse();
            response.setCharacterEncoding(Charsets.UTF_8.toString());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
            response.getWriter().print(JSON.toJSONString(ImmutableMap.of("message", e.getMessage())));
        } catch (Exception e1) {
            logger.error("在BaseExceptionHandler遇到异常", e1);

        }

    }
}

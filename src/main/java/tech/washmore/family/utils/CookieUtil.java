package tech.washmore.family.utils;

import org.apache.commons.lang3.StringUtils;
import tech.washmore.family.common.Constants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie 辅助工具
 *
 * @author Jerry.hu (SE)
 * @summary 辅助工具
 * @Copyright (c) 2017, Lianjia Group All Rights Reserved.
 * @Description 辅助工具
 * @since 2017-09-08 12:58
 */
public class CookieUtil {
    /**
     * 设置cookie
     *
     * @param response
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(!Constants.COOKIE_TOKEN_KEY.equals(name));
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 设置cookie
     *
     * @param name cookie名字
     */
    public static void removeCookie(String name) {
        addCookie(SessionUtil.getHttpResponse(), name, StringUtils.EMPTY, 0);
    }

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name    cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 根据名字获取当前请求的cookie信息
     *
     * @param name cookie名字
     * @return
     */
    public static Cookie getCurrentCookieByName(String name) {
        return getCookieByName(SessionUtil.getHttpRequest(), name);
    }


    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}

package tech.washmore.family.common.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import tech.washmore.family.common.Constants;
import tech.washmore.family.common.uc.MemeryTokenManger;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Washmore
 * @version V1.0
 * @summary 登录拦截器
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/17
 */
public class LoginInterceptor extends BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private MemeryTokenManger memeryTokenManger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String remoteIP = request.getHeader(Constants.X_REAL_IP);
        if (StringUtils.isBlank(remoteIP)) {
            remoteIP = request.getRemoteAddr();
        }
        LOGGER.info("--login check begin:[{}],ip:[{}]", request.getRequestURI(), remoteIP);

        //禁止保留参数名传入
        Object memberAccount = request.getAttribute(Constants.REQUEST_MEMBER_ACCOUNT);
        Object memberId = request.getAttribute(Constants.REQUEST_MEMBER_ID);
        if (!request.getRequestURI().equals("/error") && (memberAccount != null || memberId != null)) {
            errorRequestParamOfLoginUserCode(response);
            return false;
        }

        //FIXME 兼容微信小程序,未作充分测试验证
        //微信小程序没有cookie,所以改为从header传递token,使用nginx或apache等作为负载工具的话注意转发此自定义header
        String token = request.getHeader(Constants.COOKIE_TOKEN_KEY);
        if (StringUtils.isEmpty(token)) {
            Cookie tokenCk = CookieUtil.getCurrentCookieByName(Constants.COOKIE_TOKEN_KEY);
            if (tokenCk != null) {
                token = tokenCk.getValue();
            }
        }

        if (StringUtils.isNotEmpty(token)) {
            Familymember loginFamilyMember = memeryTokenManger.getLoginMemberByToken(token);
            if (loginFamilyMember == null) {
                LOGGER.warn("token:[{}]解析失败,登录失败!", token);
                unauthorized(response);
                return false;
            }

            //登陆成功后,set两个快捷获取登录用户的参数,在controller中使用@RequestAttribute(Constants.REQUEST_MEMBER_ID)的形式获取
            request.setAttribute(Constants.REQUEST_MEMBER_ACCOUNT, loginFamilyMember.getAccount());
            request.setAttribute(Constants.REQUEST_MEMBER_ID, loginFamilyMember.getId());

            LOGGER.info("--login check success:[{}],ip:[{}],userName:[{}],userAccount:[{}]", request.getRequestURI(), remoteIP, loginFamilyMember.getName(), loginFamilyMember.getAccount());
            return true;
        }
        LOGGER.warn("token为空,登录失败!");
        unauthorized(response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

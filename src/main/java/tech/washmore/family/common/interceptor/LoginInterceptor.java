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

public class LoginInterceptor extends BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);
    @Autowired
    private MemeryTokenManger memeryTokenManger;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        Object memberAccount = request.getAttribute(Constants.REQUEST_MEMBER_ACCOUNT);
        Object memberId = request.getAttribute(Constants.REQUEST_MEMBER_ID);

        if (!request.getRequestURI().equals("/error") && (memberAccount != null || memberId != null)) {
            errorRequestParamOfLoginUserCode(response);
            return false;
        }
        //FIXME 兼容微信小程序,未作充分测试验证
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
            request.setAttribute(Constants.REQUEST_MEMBER_ACCOUNT, loginFamilyMember.getAccount());
            request.setAttribute(Constants.REQUEST_MEMBER_ID, loginFamilyMember.getId());

            //TODO 挤掉当前成员在其他客户端的登陆

            LOGGER.info("--request:[{}],ip:[{}],userName:[{}],userAccount:[{}]", request.getRequestURI(), request.getRemoteAddr(), loginFamilyMember.getName(), loginFamilyMember.getAccount());
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

package tech.washmore.family.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import tech.washmore.family.common.Constants;
import tech.washmore.family.common.uc.MemeryTokenManger;
import tech.washmore.family.logic.GetFamilymemberByAccountAndPasswordLogic;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.utils.CookieUtil;
import tech.washmore.family.utils.OkHttpUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Washmore
 * @version V1.0
 * @summary 登陆控制器
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/18
 */
@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Value("${wx.urls.auth}")
    private String wxAuthUrl;
    @Autowired
    private MemeryTokenManger memeryTokenManger;
    @Autowired
    private GetFamilymemberByAccountAndPasswordLogic getFamilymemberByAccountAndPasswordLogic;

    /**
     * @summary web端根据账号和密码登陆
     * @version V1.0
     * @author Washmore
     * @since 2018/1/18
     */
    @PostMapping({"/login"})
    public boolean login(@RequestBody Familymember familymember, HttpServletResponse response) throws Exception {
        String token = memeryTokenManger.createToken(familymember.getAccount(), familymember.getPassword());
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        CookieUtil.addCookie(response, Constants.COOKIE_TOKEN_KEY, token, Constants.COOKIE_TOKEN_MAXAGE);
        return true;
    }

    /**
     * @summary wx小程序端根据微信派发的code(五分钟有效期)获取openId并校验登陆
     * @version V1.0
     * @author Washmore
     * @since 2018/1/18
     */
    @PostMapping({"/login4Wx"})
    public String login4Wx(@RequestParam String code) {
        JSONObject result = JSON.parseObject(OkHttpUtil.getNewCall(String.format(wxAuthUrl, code)));
        String openId = result.getString("openid");
        String token = memeryTokenManger.createToken4Wx(openId);
        return token;
    }

    /**
     * @summary 验证token, 如果通过的的话,返回登陆成员信息
     * @version V1.0
     * @author Washmore
     * @since 2018/1/18
     */
    @GetMapping({"/verifyToken"})
    public Familymember verifyToken() {
        Cookie token = CookieUtil.getCurrentCookieByName(Constants.COOKIE_TOKEN_KEY);
        return memeryTokenManger.getLoginMemberByToken(token.getValue());
    }

    /**
     * @summary 修改密码时验证原始密码
     * @version V1.0
     * @author Washmore
     * @since 2018/1/18
     */
    @PostMapping({"/verifyPassword"})
    public boolean verifyPassword(@RequestBody Familymember oldPasswordMember,
                                  @RequestAttribute(Constants.REQUEST_MEMBER_ACCOUNT) String account) {
        if (oldPasswordMember.getPassword() == null) {
            return false;
        }
        return getFamilymemberByAccountAndPasswordLogic.getFamilymemberByAccountAndPassword(account, oldPasswordMember.getPassword()) != null;
    }
}

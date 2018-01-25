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

@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Value("${wx.urls.auth}")
    private String wxAuthUrl;
    @Autowired
    private MemeryTokenManger memeryTokenManger;
    @Autowired
    private GetFamilymemberByAccountAndPasswordLogic getFamilymemberByAccountAndPasswordLogic;

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
     * 看来需要放在header里面兼容wx了  妈卖批
     *
     * @return
     * @throws Exception
     */
    @PostMapping({"/login4Wx"})
    public String login4Wx(@RequestParam String code) {
        JSONObject result = JSON.parseObject(OkHttpUtil.getNewCall(String.format(wxAuthUrl, code)));
        String openId = result.getString("openid");
        String token = memeryTokenManger.createToken4Wx(openId);
        return token;
    }

    @GetMapping({"/verifyToken"})
    public Familymember verifyToken() {
        Cookie token = CookieUtil.getCurrentCookieByName(Constants.COOKIE_TOKEN_KEY);
        return memeryTokenManger.getLoginMemberByToken(token.getValue());
    }

    @PostMapping({"/verifyPassword"})
    public boolean verifyPassword(@RequestBody Familymember oldPasswordMember,
                                  @RequestAttribute(Constants.REQUEST_MEMBER_ACCOUNT) String account) {
        if (oldPasswordMember.getPassword() == null) {
            return false;
        }
        return getFamilymemberByAccountAndPasswordLogic.getFamilymemberByAccountAndPassword(account, oldPasswordMember.getPassword()) != null;
    }
}

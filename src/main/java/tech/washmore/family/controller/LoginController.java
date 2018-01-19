package tech.washmore.family.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.washmore.family.common.Constants;
import tech.washmore.family.common.uc.MemeryTokenManger;
import tech.washmore.family.logic.GetFamilymemberByAccountAndPasswordLogic;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.service.FamilymemberService;
import tech.washmore.family.utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
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

    @GetMapping({"/verifyToken"})
    public Familymember verifyToken() throws Exception {
        Cookie token = CookieUtil.getCurrentCookieByName(Constants.COOKIE_TOKEN_KEY);
        return memeryTokenManger.getLoginMemberByToken(token.getValue());
    }

    @PostMapping({"/verifyPassword"})
    public boolean verifyPassword(@RequestBody Familymember oldPasswordMember, @RequestAttribute(Constants.REQUEST_MEMBER_ACCOUNT) String account) throws Exception {
        if (oldPasswordMember.getPassword() == null) {
            return false;
        }
        return getFamilymemberByAccountAndPasswordLogic.getFamilymemberByAccountAndPassword(account, oldPasswordMember.getPassword()) != null;
    }
}

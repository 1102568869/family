package tech.washmore.family.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.family.common.Constants;
import tech.washmore.family.common.uc.MemeryTokenManger;
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
    public Familymember login(HttpServletRequest request) throws Exception {
        Cookie token = CookieUtil.getCookieByName(request, Constants.COOKIE_TOKEN_KEY);
        return memeryTokenManger.getLoginMemberByToken(token.getValue());
    }
}

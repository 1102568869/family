package tech.washmore.family.common.uc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.logic.GetAllFamilymembersLogic;
import tech.washmore.family.logic.GetFamilymemberByAccountAndPasswordLogic;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.service.FamilymemberService;
import tech.washmore.family.utils.CookieUtil;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/17
 */
@Component
public class MemeryTokenManger {
    @Autowired
    private GetFamilymemberByAccountAndPasswordLogic getFamilymemberByAccountAndPasswordLogic;
    private static final Map<String, LoginFamilyMember> loginMembers = new HashMap<>();
    private static final long EXPIRE_TWO_HOURS = 2L * 60 * 60 * 1000;

    public Familymember getLoginMemberByToken(String token) {
        LoginFamilyMember loginFamilyMember = loginMembers.get(token);
        if (loginFamilyMember == null) {
            return null;
        }
        long current = System.currentTimeMillis();
        if (current > loginFamilyMember.getExpire()) {
            loginMembers.remove(loginFamilyMember);
            return null;
        }
        loginFamilyMember.setExpire(current + EXPIRE_TWO_HOURS);
        return loginFamilyMember;
    }

    public String createToken(String account, String password) {
        Familymember familymember = getFamilymemberByAccountAndPasswordLogic.getFamilymemberByAccountAndPassword(account, password);
        if (familymember == null) {
            return null;
        }
        LoginFamilyMember loginFamilyMember = new LoginFamilyMember(familymember);
        loginFamilyMember.setExpire(System.currentTimeMillis() + EXPIRE_TWO_HOURS);
        String token = UUID.randomUUID().toString();
        loginMembers.put(token, loginFamilyMember);
        return token;
    }
}

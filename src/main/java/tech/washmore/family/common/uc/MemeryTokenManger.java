package tech.washmore.family.common.uc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.washmore.family.logic.GetAllFamilymembersLogic;
import tech.washmore.family.logic.GetFamilymemberByAccountAndPasswordLogic;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.service.FamilymemberService;
import tech.washmore.family.utils.CookieUtil;

import javax.servlet.http.Cookie;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/17
 */
@Component
public class MemeryTokenManger {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemeryTokenManger.class);
    private static final Map<String, LoginFamilyMember> loginMembers = new HashMap<>();
    private static final long EXPIRE_HALF_HOUR = 30L * 60 * 1000;

    @Autowired
    private GetFamilymemberByAccountAndPasswordLogic getFamilymemberByAccountAndPasswordLogic;

    public Familymember getLoginMemberByToken(String token) {
        LoginFamilyMember loginFamilyMember = loginMembers.get(token);
        if (loginFamilyMember == null) {
            LOGGER.info("MemeryTokenManger-getLoginMemberByToken:token[{}]不存在!", token);
            return null;
        }
        long current = System.currentTimeMillis();
        if (current > loginFamilyMember.getExpire()) {
            loginMembers.remove(loginFamilyMember);
            LOGGER.info("MemeryTokenManger-getLoginMemberByToken:token[{}]已过期,被移除!对应成员[{}]", token, loginFamilyMember.getName());
            return null;
        }
        loginFamilyMember.setExpire(current + EXPIRE_HALF_HOUR);
        LOGGER.info("MemeryTokenManger-getLoginMemberByToken:token[{}]验证通过,续期半小时!对应成员[{}]", token, loginFamilyMember.getName());
        return loginFamilyMember;
    }

    public String createToken(String account, String password) {
        Familymember familymember = getFamilymemberByAccountAndPasswordLogic.getFamilymemberByAccountAndPassword(account, password);
        if (familymember == null) {
            LOGGER.info("MemeryTokenManger-createToken:account[{}],password[{}]验证失败!", account, password);
            return null;
        }
        LoginFamilyMember loginFamilyMember = new LoginFamilyMember(familymember);
        String token = UUID.randomUUID().toString();

        loginFamilyMember.setExpire(System.currentTimeMillis() + EXPIRE_HALF_HOUR);
        loginFamilyMember.setToken(token);
        LOGGER.info("MemeryTokenManger-createToken:account[{}],password[{}]成功创建token[{}]!对应成员[{}]", account, password, token, loginFamilyMember.getName());
        loginMembers.put(token, loginFamilyMember);
        return token;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void checkExpireMembers() {
        loginMembers.values().stream().filter(m -> System.currentTimeMillis() > m.getExpire()).map(LoginFamilyMember::getToken).collect(Collectors.toList()).forEach(token -> {
            LOGGER.info("MemeryTokenManger-checkExpireMembers:token[{}]已过期,被移除!对应成员[{}]", token, loginMembers.get(token).getName());
            loginMembers.remove(token);
        });
    }
}

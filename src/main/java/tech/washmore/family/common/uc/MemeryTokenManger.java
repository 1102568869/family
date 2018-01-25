package tech.washmore.family.common.uc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.washmore.family.logic.GetFamilymemberByAccountAndPasswordLogic;
import tech.washmore.family.logic.GetFamilymemberByWxOpenIdLogic;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.utils.CookieUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Washmore
 * @version V1.0
 * @summary 基于内存实现的token管理器(或许该写一个接口类用于规范, 然后给不同的实现 ? 内存版的显然不适用于多台节点负载的情况)
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/17
 */
@Component
public class MemeryTokenManger {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemeryTokenManger.class);
    private static final Map<String, LoginFamilyMember> loginMembers = new HashMap<>();
    private static final long EXPIRE_HALF_HOUR = 30L * 60 * 1000;

    @Autowired
    private GetFamilymemberByAccountAndPasswordLogic getFamilymemberByAccountAndPasswordLogic;
    @Autowired
    private GetFamilymemberByWxOpenIdLogic getFamilymemberByWxOpenIdLogic;


    public Familymember getLoginMemberByToken(String token) {
        LoginFamilyMember loginFamilyMember = loginMembers.get(token);
        if (loginFamilyMember == null) {
            CookieUtil.removeCookie(token);
            LOGGER.info("MemeryTokenManger-getLoginMemberByToken:token[{}]不存在!", token);
            return null;
        }
        long current = System.currentTimeMillis();
        if (current > loginFamilyMember.getExpire()) {
            loginMembers.remove(loginFamilyMember);
            CookieUtil.removeCookie(token);
            LOGGER.info("MemeryTokenManger-getLoginMemberByToken:token[{}]已过期,被移除!对应成员[{}]", token, loginFamilyMember.getName());
            return null;
        }
        loginFamilyMember.setExpire(current + EXPIRE_HALF_HOUR);
        LOGGER.info("MemeryTokenManger-getLoginMemberByToken:token[{}]验证通过,续期半小时!对应成员[{}]", token, loginFamilyMember.getName());
        return loginFamilyMember;
    }

    /**
     * 待只支持单处登陆参数的创建token方法(实现原理是移除此account的其他token)
     *
     * @param account
     * @param password
     * @param singleFlag
     * @return
     */
    public String createTokenWithSingleFlag(String account, String password, boolean singleFlag) {
        Familymember familymember = getFamilymemberByAccountAndPasswordLogic.getFamilymemberByAccountAndPassword(account, password);
        if (familymember == null) {
            LOGGER.info("MemeryTokenManger-createTokenWithSingleFlag:account[{}]验证失败!", account);
            return null;
        }
        return this.generateToken(singleFlag, familymember);
    }


    public String createToken4Wx(String openId) {
        Familymember familymember = getFamilymemberByWxOpenIdLogic.getFamilymemberByWxOpenId(openId);
        if (familymember == null) {
            LOGGER.info("MemeryTokenManger-createToken4Wx:openId[{}]验证失败!", openId);
            return null;
        }
        return this.generateToken(true, familymember);
    }


    private String generateToken(boolean singleFlag, Familymember familymember) {
        LoginFamilyMember loginFamilyMember = new LoginFamilyMember(familymember);
        String token = UUID.randomUUID().toString();

        loginFamilyMember.setExpire(System.currentTimeMillis() + EXPIRE_HALF_HOUR);
        loginFamilyMember.setToken(token);
        if (singleFlag) {
            loginMembers.values().stream().filter(m -> m.getAccount().equals(familymember.getAccount())).map(LoginFamilyMember::getToken).collect(Collectors.toList()).forEach(t -> {
                LOGGER.info("MemeryTokenManger-createTokenWithSingleFlag:account[{}]成功移除其他的token[{}]!对应成员[{}]", loginFamilyMember.getAccount(), t, loginFamilyMember.getName());
                loginMembers.remove(t);
            });
        }
        LOGGER.info("MemeryTokenManger-createTokenWithSingleFlag:account[{}],成功创建token[{}]!对应成员[{}]", loginFamilyMember.getAccount(), token, loginFamilyMember.getName());
        loginMembers.put(token, loginFamilyMember);
        return token;
    }

    /**
     * 默认只支持单处登录
     *
     * @param account
     * @param password
     * @return
     */
    public String createToken(String account, String password) {
        return createTokenWithSingleFlag(account, password, true);
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void checkExpireMembers() {
        loginMembers.values().stream().filter(m -> System.currentTimeMillis() > m.getExpire()).map(LoginFamilyMember::getToken).collect(Collectors.toList()).forEach(token -> {
            LOGGER.info("MemeryTokenManger-checkExpireMembers:token[{}]已过期,被移除!对应成员[{}]", token, loginMembers.get(token).getName());
            loginMembers.remove(token);
        });
    }
}

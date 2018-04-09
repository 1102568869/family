package tech.washmore.family.v2.common.uc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import tech.washmore.family.v2.model.BookUserBase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/9
 */
@Component
public class TokenManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenManager.class);

    private static final Map<String, LoginUserToken> memoryTokens = new HashMap<>();
    private static final long EXPIRE_HALF_HOUR = 30L * 60 * 1000;

    public static final String SOURCE_WX_XCX = "wx_xcx";
    public static final String SOURCE_WEB_PC = "web_pc";

    public String generateToken4WebPc(BookUserBase user) {
        return this.generateToken(user, SOURCE_WEB_PC);
    }

    private String generateToken(BookUserBase user, String source) {
        String token = UUID.randomUUID().toString();
        LoginUserToken loginUserToken = new LoginUserToken();
        loginUserToken.setToken(token);
        loginUserToken.setExpire(System.currentTimeMillis() + EXPIRE_HALF_HOUR);
        loginUserToken.setSource(source);
        loginUserToken.setUser(user);
        memoryTokens.values().stream()
                .filter(ut -> ut.getUser().getAccount().equals(user.getAccount()) && ut.getSource().equals(source))
                .collect(Collectors.toList())
                .forEach(ut -> {
                    LOGGER.info("TokenManager-generateToken:成员[{}],account[{}]成功移除相同source[{}]的其他的token[{}]!对应", user.getUserName(), user.getAccount(), source, ut.getToken());
                    memoryTokens.remove(ut.getToken());
                });
        memoryTokens.put(token, loginUserToken);
        return token;
    }
}

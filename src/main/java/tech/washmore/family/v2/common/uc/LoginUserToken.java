package tech.washmore.family.v2.common.uc;

import tech.washmore.family.v2.model.BookUserBase;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/9
 */
public class LoginUserToken {
    private String userAccount;
    private String token;
    private long expire;
    private String source;

    private BookUserBase user;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BookUserBase getUser() {
        return user;
    }

    public void setUser(BookUserBase user) {
        this.user = user;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}

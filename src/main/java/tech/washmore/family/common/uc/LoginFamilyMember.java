package tech.washmore.family.common.uc;

import org.springframework.beans.BeanUtils;
import tech.washmore.family.model.Familymember;

/**
 * @author Washmore
 * @version V1.0
 * @summary 登录用户model
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/17
 */
public class LoginFamilyMember extends Familymember {
    private long expire;
    private String token;
    private String source;

    public LoginFamilyMember() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LoginFamilyMember(Familymember familymember) {
        BeanUtils.copyProperties(familymember, this);
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

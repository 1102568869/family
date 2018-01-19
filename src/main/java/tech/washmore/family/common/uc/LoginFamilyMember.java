package tech.washmore.family.common.uc;

import tech.washmore.family.model.Familymember;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/17
 */
public class LoginFamilyMember extends Familymember {
    private long expire;
    private String token;

    public LoginFamilyMember() {
    }

    public LoginFamilyMember(Familymember familymember) {
        this.setId(familymember.getId());
        this.setAccount(familymember.getAccount());
        this.setName(familymember.getName());
        this.setMobile(familymember.getMobile());
        this.setEmail(familymember.getEmail());
        this.setQq(familymember.getQq());
        this.setImage(familymember.getImage());
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

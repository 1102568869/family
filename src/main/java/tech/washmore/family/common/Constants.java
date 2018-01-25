package tech.washmore.family.common;

/**
 * @author Washmore
 * @version V1.0
 * @summary 常用静态常量
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/17
 */
public class Constants {
    /**
     * request中用于身份识别的key
     */
    public static final String COOKIE_TOKEN_KEY = "family_token";
    /**
     * 默认token过期时间为一周
     */
    public static final int COOKIE_TOKEN_MAXAGE = 7 * 24 * 60 * 60;//s为单位
    /**
     * 标注登录用户的account,用于在controller通过使用@RequestAttribute(Constants.REQUEST_MEMBER_ACCOUNT)的形式快捷获取使用
     */
    public static final String REQUEST_MEMBER_ACCOUNT = "X_MEMBER_ACCOUNT";
    /**
     * 标注登录用户的id,用于在controller通过使用@RequestAttribute(Constants.REQUEST_MEMBER_ID)的形式快捷获取使用
     */
    public static final String REQUEST_MEMBER_ID = "X_MEMBER_ID";
}

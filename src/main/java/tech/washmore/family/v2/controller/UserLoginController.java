package tech.washmore.family.v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.family.v2.common.uc.TokenManager;
import tech.washmore.family.v2.model.BookUserBase;
import tech.washmore.family.v2.model.BookUserPassword;
import tech.washmore.family.v2.service.BookUserBaseService;
import tech.washmore.family.v2.service.BookUserPasswordService;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/9
 */
@RestController
@RequestMapping("/userlogin")
public class UserLoginController {
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private BookUserPasswordService bookUserPasswordService;
    @Autowired
    private BookUserBaseService bookUserBaseService;

    /**
     * @summary web端根据账号和密码登陆
     * @version V1.0
     * @author Washmore
     * @since 2018/1/18
     */
    @PostMapping({"/webpc"})
    public String login(@RequestBody BookUserPassword userPassword) {

        BookUserPassword existUserPassword = bookUserPasswordService.getBookUserPasswordByAccountAndPassword(userPassword.getAccount(), userPassword.getPassword());
        if (existUserPassword == null) {
            return null;
        }
        BookUserBase existUser = bookUserBaseService.getBookUserBaseByAccount(existUserPassword.getAccount());
        if (existUser == null) {
            return null;
        }
        return tokenManager.generateToken4WebPc(existUser);
    }
}

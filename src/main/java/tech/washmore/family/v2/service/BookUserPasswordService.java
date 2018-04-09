package tech.washmore.family.v2.service;

import org.springframework.stereotype.Service;
import tech.washmore.family.v2.dao.BookUserPasswordMapper;
import tech.washmore.family.v2.logic.BookUserPasswordLogic;
import tech.washmore.family.v2.model.BookUserPassword;

import javax.annotation.Resource;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/8
 */
@Service
public class BookUserPasswordService {
    @Resource
    private BookUserPasswordMapper bookUserPasswordMapper;
    @Resource
    private BookUserPasswordLogic bookUserPasswordLogic;

    public BookUserPassword getBookUserPasswordByAccountAndPassword(String account, String password) {
        return bookUserPasswordLogic.getBookUserPasswordByAccountAndPassowrd(account, password);
    }

}
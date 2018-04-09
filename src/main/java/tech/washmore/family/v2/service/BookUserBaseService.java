package tech.washmore.family.v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.washmore.family.v2.logic.BookUserBaseLogic;
import tech.washmore.family.v2.model.BookUserBase;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/8
 */
@Service
public class BookUserBaseService {
    @Autowired
    private BookUserBaseLogic bookUserBaseLogic;


    public BookUserBase getBookUserBaseByAccount(String account) {
        return bookUserBaseLogic.getBookUserBaseByAccount(account);
    }

}
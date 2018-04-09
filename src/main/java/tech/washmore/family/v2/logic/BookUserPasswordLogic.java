package tech.washmore.family.v2.logic;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import tech.washmore.family.v2.dao.BookUserPasswordMapper;
import tech.washmore.family.v2.model.BookUserPassword;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/9
 */
@Component
public class BookUserPasswordLogic {
    @Resource
    private BookUserPasswordMapper bookUserPasswordMapper;

    public BookUserPassword getBookUserPasswordByAccountAndPassowrd(String account, String password) {
        List<BookUserPassword> list = bookUserPasswordMapper.selectByParams(ImmutableMap.of("account", account, "password", password));
        if (CollectionUtils.size(list) == 1) {
            return list.get(0);
        }
        return null;
    }
}

package tech.washmore.family.v2.logic;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import tech.washmore.family.v2.dao.BookUserBaseMapper;
import tech.washmore.family.v2.model.BookUserBase;
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
public class BookUserBaseLogic {
    @Resource
    private BookUserBaseMapper bookUserBaseMapper;

    public BookUserBase getBookUserBaseByAccount(String account) {
        List<BookUserBase> list = bookUserBaseMapper.selectByParams(ImmutableMap.of("account", account));
        if (CollectionUtils.size(list) == 1) {
            return list.get(0);
        }
        return null;
    }
}

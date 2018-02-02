package tech.washmore.family.logic;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillTagDao;
import tech.washmore.family.model.Billtag;

import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/2/2
 */
@Component
public class GetTopBilltagsByKeywordLogic {
    @Autowired
    private BillTagDao billTagDao;

    public List<Billtag> getTopBilltagsByKeyword(String keyword, int offset) {
        return billTagDao.findBilltagsByParams(ImmutableMap.of("keyword", keyword, "offset", offset));
    }
}

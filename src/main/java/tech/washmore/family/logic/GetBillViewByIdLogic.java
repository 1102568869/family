package tech.washmore.family.logic;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillDao;
import tech.washmore.family.model.view.BillView;

import java.util.Collection;
import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/15
 */
@Component
public class GetBillViewByIdLogic {
    @Autowired
    private BillDao billDao;

    public BillView getBillViewById(int id) {
        List<BillView> list = billDao.findBillViewsByParams(ImmutableMap.of("id", id));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
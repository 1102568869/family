package tech.washmore.family.logic;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillDao;

import java.util.List;
import java.util.Map;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/2/1
 */
@Component
public class GetBillViewsTotalLogic {
    @Autowired
    private BillDao billDao;

    public Integer getBillsTotal(Integer memberId, Integer typeId, List<Integer> tagIds) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("member", memberId);
        params.put("type", typeId);
        params.put("tagIds", tagIds);
        return billDao.countBillsByParams(params);
    }
}

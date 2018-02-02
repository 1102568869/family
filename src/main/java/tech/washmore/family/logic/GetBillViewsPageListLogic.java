package tech.washmore.family.logic;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillDao;
import tech.washmore.family.model.view.BillView;

import java.util.List;
import java.util.Map;

/**
 * @author Washmore
 * @version V1.0
 * @summary 按条件分页查询账单
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/2/1
 */
@Component
public class GetBillViewsPageListLogic {
    @Autowired
    private BillDao billDao;

    public List<BillView> getBillViewsPageList(int pageSize, int pageNo, Integer memberId, Integer typeId, List<Integer> tagIds) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("start", pageSize * (pageNo - 1));
        params.put("offset", pageSize);
        params.put("member", memberId);
        params.put("type", typeId);
        params.put("tagIds", tagIds);

        return billDao.findBillViewsByParams(params);
    }


}

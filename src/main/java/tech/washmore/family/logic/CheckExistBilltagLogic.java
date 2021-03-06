package tech.washmore.family.logic;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillTagDao;
import tech.washmore.family.dao.FamilymemberDao;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.model.Familymember;

import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/19
 */
@Component
public class CheckExistBilltagLogic {
    @Autowired
    private BillTagDao billTagDao;

    public Billtag checkExistBilltag(Billtag billtag) {
        List<Billtag> list = billTagDao.findBilltagsByParams(ImmutableMap.of("name", billtag.getName()));
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        Integer id = billtag.getId();
        if (id == null) {
            return list.get(0);
        }
        return list.stream().filter(m -> !m.getId().equals(id)).findFirst().orElse(null);
    }
}

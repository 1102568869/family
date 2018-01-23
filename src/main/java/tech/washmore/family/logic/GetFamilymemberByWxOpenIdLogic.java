package tech.washmore.family.logic;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.FamilymemberDao;
import tech.washmore.family.model.Familymember;

import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/23
 */
@Component
public class GetFamilymemberByWxOpenIdLogic {
    @Autowired
    private FamilymemberDao familymemberDao;

    public Familymember getFamilymemberByWxOpenId(String openId) {
        List<Familymember> familymembers = familymemberDao.findFamilymembersByParams(ImmutableMap.of("openId", openId));
        if (CollectionUtils.isEmpty(familymembers)) {
            return null;
        }
        return familymembers.get(0);
    }
}

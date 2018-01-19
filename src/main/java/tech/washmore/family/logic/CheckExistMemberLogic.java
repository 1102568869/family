package tech.washmore.family.logic;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.FamilymemberDao;
import tech.washmore.family.model.Familymember;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/19
 */
@Component
public class CheckExistMemberLogic {
    @Autowired
    private FamilymemberDao familymemberDao;

    public boolean checkExistMember(Familymember familymember) {
        List<Familymember> list = familymemberDao.findFamilymembersByParams(ImmutableMap.of("account", familymember.getAccount()));
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        Integer id = familymember.getId();
        if (id == null) {
            return true;
        }
        return list.stream().anyMatch(m -> !m.getId().equals(id));
    }
}

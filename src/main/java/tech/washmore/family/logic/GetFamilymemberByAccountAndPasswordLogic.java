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
 * @since 2018/1/17
 */
@Component
public class GetFamilymemberByAccountAndPasswordLogic {
    @Autowired
    private FamilymemberDao familymemberDao;

    public Familymember getFamilymemberByAccountAndPassword(String account, String password) {
        List<Familymember> familymembers = familymemberDao.findFamilymembersByParams(ImmutableMap.of("account", account, "password", password));
        if (CollectionUtils.isEmpty(familymembers)) {
            return null;
        }
        return familymembers.get(0);
    }
}

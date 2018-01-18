package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.FamilymemberDao;
import tech.washmore.family.model.Familymember;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/18
 */
@Component
public class AddFamilymemberLogic {
    @Autowired
    private FamilymemberDao familymemberDao;

    public boolean addFamilymember(Familymember familymember) {

        return familymemberDao.addFamilymember(familymember) > 0;
    }
}

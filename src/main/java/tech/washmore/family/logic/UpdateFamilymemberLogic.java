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
 * @since 2018/1/19
 */
@Component
public class UpdateFamilymemberLogic {
    @Autowired
    private FamilymemberDao familymemberDao;

    public boolean updateFamilymember(Familymember familymember) {
        return familymemberDao.updateFamilymember(familymember) > 0;
    }
}

package tech.washmore.family.logic;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.FamilymemberDao;
import tech.washmore.family.model.Familymember;

import java.util.List;

@Component
public class GetFirstFamilymemberLogic {
    @Autowired
    private FamilymemberDao familymemberDao;

    public Familymember getFirstFamilymember() {
        List<Familymember> familymembers = familymemberDao.findFamilymembersByParams(null);
        if (CollectionUtils.isEmpty(familymembers)) {
            return null;
        }
        return familymembers.get(0);
    }
}

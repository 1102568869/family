package tech.washmore.family.logic;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.FamilymemberDao;
import tech.washmore.family.model.Familymember;

import java.util.List;

@Component
public class GetAllFamilymembersLogic {
    @Autowired
    private FamilymemberDao familymemberDao;

    public List<Familymember> getAllFamilymembers() {
        return familymemberDao.findFamilymembersByParams(null);

    }
}

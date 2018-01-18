package tech.washmore.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.washmore.family.logic.AddFamilymemberLogic;
import tech.washmore.family.logic.GetAllFamilymembersLogic;
import tech.washmore.family.logic.GetFirstFamilymemberLogic;
import tech.washmore.family.model.Familymember;

import java.util.List;

@Service
public class FamilymemberService {
    @Autowired
    private GetFirstFamilymemberLogic getFirstFamilymemberLogic;
    @Autowired
    private GetAllFamilymembersLogic getAllFamilymembersLogic;
    @Autowired
    private AddFamilymemberLogic addFamilymemberLogic;

    public Familymember getFirstFamilymember() {
        return getFirstFamilymemberLogic.getFirstFamilymember();
    }

    public List<Familymember> getAllFamilymembers() {
        return getAllFamilymembersLogic.getAllFamilymembers();
    }

    public boolean addFamilymember(Familymember familymember) {
        return addFamilymemberLogic.addFamilymember(familymember);
    }
}

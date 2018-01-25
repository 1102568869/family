package tech.washmore.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.washmore.family.logic.ChangePasswordLogic;
import tech.washmore.family.logic.*;
import tech.washmore.family.model.Familymember;

import java.util.List;

@Service
public class FamilymemberService {
    @Autowired
    private GetFirstFamilymemberLogic getFirstFamilymemberLogic;
    @Autowired
    private GetAllFamilymembersLogic getAllFamilymembersLogic;
    @Autowired
    private GetFamilymemberByIdLogic getFamilymemberByIdLogic;
    @Autowired
    private AddFamilymemberLogic addFamilymemberLogic;
    @Autowired
    private CheckExistMemberLogic checkExistMemberLogic;
    @Autowired
    private UpdateFamilymemberLogic updateFamilymemberLogic;
    @Autowired
    private ChangePasswordLogic changePasswordLogic;

    @Deprecated
    public Familymember getFirstFamilymember() {
        return getFirstFamilymemberLogic.getFirstFamilymember();
    }

    public List<Familymember> getAllFamilymembers() {
        return getAllFamilymembersLogic.getAllFamilymembers();
    }

    public boolean addFamilymember(Familymember familymember) {
        return addFamilymemberLogic.addFamilymember(familymember);
    }

    public boolean updateFamilymember(Familymember familymember) {
        return updateFamilymemberLogic.updateFamilymember(familymember);
    }

    public Familymember getFamilymemberById(int id) {
        return getFamilymemberByIdLogic.getFamilymemberById(id);
    }

    public boolean checkExistMember(Familymember familymember) {
        return checkExistMemberLogic.checkExistMember(familymember) != null;
    }

    public boolean changePassword(Familymember familymember) {
        return changePasswordLogic.changePassword(familymember);
    }
}

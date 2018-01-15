package tech.washmore.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.washmore.family.logic.AddBillLogic;
import tech.washmore.family.logic.GetAllBilltypesLogic;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.Billtype;

import java.util.List;

@Service
public class BilltypeService {
    @Autowired
    private GetAllBilltypesLogic getAllBilltypesLogic;

    public List<Billtype> getAllBilltypes() {
        return getAllBilltypesLogic.getAllBilltypes();
    }

}

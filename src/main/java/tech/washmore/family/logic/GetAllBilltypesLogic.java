package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BilltypeDao;
import tech.washmore.family.model.Billtype;

import java.util.List;

@Component
public class GetAllBilltypesLogic {
    @Autowired
    private BilltypeDao billtypeDao;

    public List<Billtype> getAllBilltypes() {
        return billtypeDao.findBilltypesByParams(null);
    }
}

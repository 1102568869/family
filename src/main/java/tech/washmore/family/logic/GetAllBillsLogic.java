package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillDao;
import tech.washmore.family.dao.BilltypeDao;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.Billtype;

import java.util.List;

@Component
public class GetAllBillsLogic {
    @Autowired
    private BillDao billDao;

    public List<Bill> getAllBilltypes() {
        return billDao.findBillsByParams(null);
    }
}

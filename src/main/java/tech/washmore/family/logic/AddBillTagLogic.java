package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillTagDao;
import tech.washmore.family.model.Billtag;

import java.util.List;

@Component
public class AddBillTagLogic {
    @Autowired
    private BillTagDao billTagDao;

    public int addBilltag(Billtag billtag) {
        return billTagDao.addBilltag(billtag);
    }
}

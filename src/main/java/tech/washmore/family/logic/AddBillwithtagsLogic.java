package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillwithtagsDao;
import tech.washmore.family.model.Billwithtags;

@Component
public class AddBillwithtagsLogic {
    @Autowired
    private BillwithtagsDao billwithtagsDao;

    public int addBillwithtags(Billwithtags bwt) {
        return billwithtagsDao.addBillwithtags(bwt);
    }
}

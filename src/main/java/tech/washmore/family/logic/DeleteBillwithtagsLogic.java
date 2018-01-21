package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillwithtagsDao;
import tech.washmore.family.model.Billwithtags;

@Component
public class DeleteBillwithtagsLogic {
    @Autowired
    private BillwithtagsDao billwithtagsDao;

    public int deleteBillwithtags(Billwithtags billwithtags) {
       return billwithtagsDao.deleteBillwithtags(billwithtags);
    }
}

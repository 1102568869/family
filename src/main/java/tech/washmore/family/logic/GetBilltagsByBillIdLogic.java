package tech.washmore.family.logic;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillTagDao;
import tech.washmore.family.dao.BilltypeDao;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.model.Billtype;

import java.util.List;

@Component
public class GetBilltagsByBillIdLogic {
    @Autowired
    private BillTagDao billTagDao;

    public List<Billtag> getBilltagsByBillId(int billId) {
        return billTagDao.findBilltagsByParams(ImmutableMap.of("billId", billId));
    }

    public List<Billtag> getAllBilltags() {
        return billTagDao.findBilltagsByParams(null);
    }
}

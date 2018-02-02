package tech.washmore.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.washmore.family.logic.GetBilltagsByBillIdLogic;
import tech.washmore.family.logic.GetTopBilltagsByKeywordLogic;
import tech.washmore.family.model.Billtag;

import java.util.List;

@Service
public class BilltagService {
    @Autowired
    private GetBilltagsByBillIdLogic getBilltagsByBillIdLogic;
    @Autowired
    private GetTopBilltagsByKeywordLogic getTopBilltagsByKeywordLogic;

    public List<Billtag> getBilltagsByBillId(int billId) {
        return getBilltagsByBillIdLogic.getBilltagsByBillId(billId);
    }

    public List<Billtag> getTopBilltagsByKeyword(String keyword, int top) {
        return getTopBilltagsByKeywordLogic.getTopBilltagsByKeyword(keyword, top);
    }

    public List<Billtag> getAllBilltags() {
        return getBilltagsByBillIdLogic.getAllBilltags();
    }


}

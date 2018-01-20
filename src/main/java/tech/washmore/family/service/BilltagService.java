package tech.washmore.family.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.washmore.family.logic.GetAllBilltypesLogic;
import tech.washmore.family.logic.GetBilltagsByBillIdLogic;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.model.Billtype;

import java.util.List;

@Service
public class BilltagService {
    @Autowired
    private GetBilltagsByBillIdLogic getBilltagsByBillIdLogic;

    public List<Billtag> getBilltagsByBillId(int billId) {
        return getBilltagsByBillIdLogic.getBilltagsByBillId(billId);
    }

}

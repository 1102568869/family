package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillDao;
import tech.washmore.family.model.Bill;

@Component
public class AddBillLogic {
    @Autowired
    private BillDao billDao;

    public boolean addBill(Bill bill) {
        return billDao.addBill(bill) > 0;
    }
}

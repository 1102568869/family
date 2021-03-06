package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillDao;
import tech.washmore.family.model.Bill;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/15
 */
@Component
public class UpdateBillLogic {
    @Autowired
    private BillDao billDao;

    public boolean updateBill(Bill bill) {
        return billDao.updateBill(bill) > 0;
    }
}

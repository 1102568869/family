package tech.washmore.family.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.washmore.family.dao.BillDao;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.view.BillView;

import java.util.List;

@Component
public class GetAllBillViewsLogic {
    @Autowired
    private BillDao billDao;

    public List<BillView> getAllBillViews() {
        return billDao.findBillViewsByParams(null);
    }
}

package tech.washmore.family.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.washmore.family.logic.*;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.view.BillView;

import java.util.List;

@Service
public class BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillService.class);
    @Autowired
    private AddBillLogic addBillLogic;
    @Autowired
    private GetAllBillsLogic getAllBillsLogic;
    @Autowired
    private GetAllBillViewsLogic getAllBillViewsLogic;
    @Autowired
    private GetBillViewByIdLogic getBillViewByIdLogic;
    @Autowired
    private UpdateBillLogic updateBillLogic;
    @Autowired
    private DeleteBillLogic deleteBillLogic;

    public boolean addBill(Bill bill) {
        return addBillLogic.addBill(bill);
    }

    public List<Bill> getAllBilltypes() {
        return getAllBillsLogic.getAllBilltypes();
    }

    public List<BillView> getAllBillViews() {
        return getAllBillViewsLogic.getAllBillViews();
    }

    public BillView getBillViewById(int id) {
        return getBillViewByIdLogic.getBillViewById(id);
    }


    public boolean updateBill(Bill bill) {
        return updateBillLogic.updateBill(bill);
    }

    public boolean deleteBill(int id) {
        return deleteBillLogic.deleteBill(id);
    }
}

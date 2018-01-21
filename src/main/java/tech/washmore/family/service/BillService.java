package tech.washmore.family.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.washmore.family.logic.*;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.model.Billwithtags;
import tech.washmore.family.model.view.BillView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private AddBillTagLogic addBillTagLogic;
    @Autowired
    private CheckExistBilltagLogic checkExistBilltagLogic;
    @Autowired
    private AddBillwithtagsLogic addBillwithtagsLogic;
    @Autowired
    private DeleteBillwithtagsLogic deleteBillwithtagsLogic;

    @Transactional
    public boolean addBill(BillView bill) {
        if (addBillLogic.addBill(bill)) {
            this.handleTags(bill);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean updateBill(BillView bill) {
        if (updateBillLogic.updateBill(bill)) {
            this.handleTags(bill);
            return true;
        }
        return false;
    }

    private void handleTags(BillView bill) {
        System.out.println(JSON.toJSONString(bill, true));
        List tagIds = bill.getTagIds();
        List<Integer> ids = new ArrayList<>();
        for (Object tagId : tagIds) {
            if (tagId instanceof Integer) {
                Integer id = (Integer) tagId;
                ids.add(id);
            } else if (tagId instanceof String) {
                String name = (String) tagId;
                Billtag create = new Billtag();
                create.setName(name);
                Billtag exists = checkExistBilltagLogic.checkExistBilltag(create);
                if (exists != null) {
                    ids.add(exists.getId());
                } else {
                    addBillTagLogic.addBilltag(create);
                    ids.add(create.getId());
                }
            }
        }
        //  List<Integer> deletes = billTagIds.stream().filter(id -> !ids.contains(id)).collect(Collectors.toList());
        //delet 一劳永逸删干净 ,然后重新插入
        Billwithtags billId = new Billwithtags();
        billId.setBill(bill.getId());
        deleteBillwithtagsLogic.deleteBillwithtags(billId);

        //add Billwithtags
        ids.stream().distinct().forEach(id -> {
            Billwithtags bwt = new Billwithtags();
            bwt.setBill(bill.getId());
            bwt.setTag(id);
            addBillwithtagsLogic.addBillwithtags(bwt);
        });
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


    public boolean deleteBill(int id) {
        return deleteBillLogic.deleteBill(id);
    }
}

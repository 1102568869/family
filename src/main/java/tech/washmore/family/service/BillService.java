package tech.washmore.family.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.washmore.family.logic.*;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.model.Billwithtags;
import tech.washmore.family.model.Page;
import tech.washmore.family.model.view.BillView;
import tech.washmore.family.utils.PageUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillService.class);
    @Autowired
    private AddBillLogic addBillLogic;
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
    @Autowired
    private GetBillViewsTotalLogic getBillViewsTotalLogic;
    @Autowired
    private GetBillViewsPageListLogic getBillViewsPageListLogic;

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
        List tagIds = bill.getTagIds();
        List<Integer> ids = new ArrayList<>();
        for (Object tagId : tagIds) {
            if (tagId instanceof Integer) {
                //已有的标签
                Integer id = (Integer) tagId;
                ids.add(id);
            } else if (tagId instanceof String) {
                //本次新增的标签
                String name = (String) tagId;
                Billtag create = new Billtag();
                create.setName(name);
                //考虑到后续可能不会一次加载所有标签到前台,所以前台没有的标签需要到db二次确认 TODO 待优化
                Billtag exists = checkExistBilltagLogic.checkExistBilltag(create);
                if (exists != null) {
                    ids.add(exists.getId());
                } else {
                    addBillTagLogic.addBilltag(create);
                    ids.add(create.getId());
                }
            }
        }
        //delete 一劳永逸删干净 ,然后重新插入
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

    public List<BillView> getAllBillViews() {
        return getAllBillViewsLogic.getAllBillViews();
    }

    public Page<BillView> getBillViewsPage(int pageSize, int pageNo, Integer memberId, Integer typeId, List<Integer> tagIds) {
        List<BillView> list = new ArrayList<>();
        Integer total = getBillViewsTotalLogic.getBillsTotal(memberId, typeId, tagIds);
        if (total > 0) {
            list = getBillViewsPageListLogic.getBillViewsPageList(pageSize, pageNo, memberId, typeId, tagIds);
        }
        return PageUtil.fillPage(list, total, pageSize, pageNo);
    }

    public BillView getBillViewById(int id) {
        return getBillViewByIdLogic.getBillViewById(id);
    }


    public boolean deleteBill(int id) {
        return deleteBillLogic.deleteBill(id);
    }
}

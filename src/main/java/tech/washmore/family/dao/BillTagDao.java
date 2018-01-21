package tech.washmore.family.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.model.Billtype;

import java.util.List;
import java.util.Map;

@Repository
public class BillTagDao extends BaseDao {
    public List<Billtag> findBilltagsByParams(Map<String, Object> params) {
        return super.getSqlSession().selectList("findBilltagsByParams", params);
    }


    public int addBilltag(Billtag billtag) {
        return super.getSqlSession().insert("addBilltag", billtag);
    }
}

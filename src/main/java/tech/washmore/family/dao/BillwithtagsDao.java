package tech.washmore.family.dao;

import org.springframework.stereotype.Repository;
import tech.washmore.family.model.Billwithtags;

@Repository
public class BillwithtagsDao extends BaseDao {
    public int addBillwithtags(Billwithtags billwithtags) {
        return super.getSqlSession().insert("addBillwithtags", billwithtags);
    }

    public int deleteBillwithtags(Billwithtags billwithtags) {
        return super.getSqlSession().delete("deleteBillwithtags", billwithtags);

    }
}

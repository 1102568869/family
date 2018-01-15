package tech.washmore.family.dao;

import org.springframework.stereotype.Repository;
import tech.washmore.family.model.Billtype;
import tech.washmore.family.model.Familymember;

import java.util.List;
import java.util.Map;

@Repository
public class BilltypeDao extends BaseDao {
    public List<Billtype> findBilltypesByParams(Map<String, Object> params) {
        return super.getSqlSession().selectList("findBilltypesByParams", params);
    }
}

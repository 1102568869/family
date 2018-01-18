package tech.washmore.family.dao;

import org.springframework.stereotype.Repository;
import tech.washmore.family.model.Familymember;

import java.util.List;
import java.util.Map;

@Repository
public class FamilymemberDao extends BaseDao {
    public List<Familymember> findFamilymembersByParams(Map<String, Object> params) {
        return super.getSqlSession().selectList("findFamilymembersByParams", params);
    }

    public int addFamilymember(Familymember familymember) {
        return super.getSqlSession().insert("addFamilymember", familymember);
    }
}

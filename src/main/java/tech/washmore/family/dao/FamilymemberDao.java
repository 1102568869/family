package tech.washmore.family.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.family.model.Familymember;

import java.util.List;
import java.util.Map;

@Repository
public class FamilymemberDao extends BaseDao {
    public List<Familymember> findFamilymembersByParams(Map<String, Object> params) {
        return super.getSqlSession().selectList("findFamilymembersByParams", params);
    }
}

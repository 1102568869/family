package tech.washmore.family.v2.dao;

import tech.washmore.family.v2.model.BookUserBase;

import java.util.List;
import java.util.Map;

public interface BookUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BookUserBase record);

    int insertSelective(BookUserBase record);

    BookUserBase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BookUserBase record);

    int updateByPrimaryKey(BookUserBase record);

    List<BookUserBase> selectByParams(Map<String, Object> params);

}
package tech.washmore.family.v2.dao;

import tech.washmore.family.v2.model.BookUserBase;

public interface BookUserBaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BookUserBase record);

    int insertSelective(BookUserBase record);

    BookUserBase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BookUserBase record);

    int updateByPrimaryKey(BookUserBase record);
}
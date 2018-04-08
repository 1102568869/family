package tech.washmore.family.v2.dao;

import tech.washmore.family.v2.model.BookBillType;

public interface BookBillTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BookBillType record);

    int insertSelective(BookBillType record);

    BookBillType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BookBillType record);

    int updateByPrimaryKey(BookBillType record);
}
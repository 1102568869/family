package tech.washmore.family.v2.dao;

import tech.washmore.family.v2.model.BookBill;

public interface BookBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BookBill record);

    int insertSelective(BookBill record);

    BookBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BookBill record);

    int updateByPrimaryKey(BookBill record);
}
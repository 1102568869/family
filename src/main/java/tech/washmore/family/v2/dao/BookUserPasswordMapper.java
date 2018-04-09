package tech.washmore.family.v2.dao;

import tech.washmore.family.v2.model.BookUserPassword;

import java.util.List;
import java.util.Map;

public interface BookUserPasswordMapper {
    int deleteByPrimaryKey(String account);

    int insert(BookUserPassword record);

    int insertSelective(BookUserPassword record);

    BookUserPassword selectByPrimaryKey(String account);

    int updateByPrimaryKeySelective(BookUserPassword record);

    int updateByPrimaryKey(BookUserPassword record);

    List<BookUserPassword> selectByParams(Map<String, Object> params);
}
package tech.washmore.family.v2.service;

import org.springframework.stereotype.Service;
import tech.washmore.family.v2.dao.BookBillTypeMapper;
import tech.washmore.family.v2.model.BookBillType;

import javax.annotation.Resource;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/8
 */
@Service
public class BookBillTypeService {
    @Resource
    private BookBillTypeMapper bookBillTypeMapper;

    public int addBookBillType(BookBillType record) {
        return bookBillTypeMapper.insertSelective(record);
    }

    public BookBillType selectByPrimaryKey(Long id) {
        return bookBillTypeMapper.selectByPrimaryKey(id);
    }
}

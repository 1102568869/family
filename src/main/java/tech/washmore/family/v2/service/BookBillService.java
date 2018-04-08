package tech.washmore.family.v2.service;

import org.springframework.stereotype.Service;
import tech.washmore.family.v2.dao.BookBillMapper;
import tech.washmore.family.v2.model.BookBill;

import javax.annotation.Resource;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/4/8
 */
@Service
public class BookBillService {
    @Resource
    private BookBillMapper bookBillMapper;



    public int addBookBill(BookBill record) {
        return bookBillMapper.insertSelective(record);
    }

    public BookBill selectByPrimaryKey(Long id) {
        return bookBillMapper.selectByPrimaryKey(id);
    }

}

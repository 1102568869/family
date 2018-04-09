package tech.washmore.family.v2.service;

import base.BaseTest;
import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.washmore.family.v2.model.BookBillType;

import java.util.Date;

/**
 * BookBillTypeService Tester.
 *
 * @author Washmore
 * @version 1.0
 * @since <pre>04/08/2018</pre>
 */
public class BookBillTypeServiceTest extends BaseTest {
    @Autowired
    private BookBillTypeService bookBillTypeService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addBookBillType(BookBillType record)
     */
    @Test
    public void testAddBookBillType() throws Exception {
        //TODO: Test goes here...
        BookBillType billType = new BookBillType();
        billType.setName("娱乐");
        billType.setCreator(1L);
        billType.setCreateName("陈雨清");
        billType.setCreateTime(new Date());
        billType.setUpdater(1L);
        billType.setUpdateName("陈雨清");
        billType.setUpdateTime(new Date());
        bookBillTypeService.addBookBillType(billType);
        System.out.println("billType:" + System.lineSeparator() + JSON.toJSONString(billType, true));
    }

    /**
     * Method: selectByPrimaryKey(Long id)
     */
    @Test
    public void testSelectByPrimaryKey() throws Exception {
        //TODO: Test goes here...
        //bookBillTypeService.selectByPrimaryKey();
    }

} 

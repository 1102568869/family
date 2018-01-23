package tech.washmore.family.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.washmore.BaseTest;


/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/22
 */
public class FamilymemberServiceTest extends BaseTest {
    @Autowired
    private FamilymemberService familymemberService;

    @Test
    public void getFirstFamilymember() {
        familymemberService.getFirstFamilymember();
    }

    @Test
    public void getAllFamilymembers() {
    }

    @Test
    public void addFamilymember() {
    }

    @Test
    public void updateFamilymember() {
    }

    @Test
    public void getFamilymemberById() {
    }

    @Test
    public void checkExistMember() {
    }

    @Test
    public void changePassword() {
    }
}
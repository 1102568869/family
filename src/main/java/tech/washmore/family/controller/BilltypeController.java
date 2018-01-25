package tech.washmore.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.family.model.Billtype;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.service.BilltypeService;
import tech.washmore.family.service.FamilymemberService;

import java.util.List;

/**
 * @author Washmore
 * @version V1.0
 * @summary 账单类型控制器
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@RestController
@RequestMapping("/billtype/")
public class BilltypeController {
    @Autowired
    private BilltypeService billtypeService;

    /**
     * @summary 查询所有的账单类型
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/all")
    public List<Billtype> getAll() {
        return billtypeService.getAllBilltypes();
    }
}

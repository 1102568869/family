package tech.washmore.family.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.family.model.Billtag;
import tech.washmore.family.service.BilltagService;
import tech.washmore.family.utils.StreamUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Washmore
 * @version V1.0
 * @summary 账单标签控制器
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@RestController
@RequestMapping("/billtag/")
public class BilltagController {
    @Autowired
    private BilltagService billtagService;

    /**
     * @summary 查询所有的?标签列表,起的啥名...
     * TODO 这里还没想好如何只推10条,以及一个bill如果超过了10条该怎么推?
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/top10")
    public List<Billtag> getTop10() {
        List<Billtag> billtags = billtagService.getAllBilltags();
        if (CollectionUtils.isEmpty(billtags)) {
            return new ArrayList<>();
        }
        return billtags.stream().sorted(Comparator.comparing(Billtag::getId).reversed()).filter(StreamUtil.distinctByKey(Billtag::getId)).collect(Collectors.toList());
    }


    /**
     * @summary 查询账单对应的标签列表
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/tags")
    public List<Billtag> getTagsOfBill(@RequestParam int billId) {
        return billtagService.getBilltagsByBillId(billId);
    }
}

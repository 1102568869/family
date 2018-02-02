package tech.washmore.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.washmore.family.common.Constants;
import tech.washmore.family.common.enums.BalanceType;
import tech.washmore.family.model.Page;
import tech.washmore.family.model.view.BillView;
import tech.washmore.family.service.BillService;
import tech.washmore.family.service.BilltagService;
import tech.washmore.family.utils.PageUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Washmore
 * @version V1.0
 * @summary 账单控制器
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private BilltagService billtagService;

    /**
     * @summary 添加账单
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @PostMapping("/post/add")
    public boolean add(@Validated @RequestBody BillView bill, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(String.join(",", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
        }

        return billService.addBill(bill);
    }

    /**
     * @summary 修改账单
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @PostMapping("/post/update")
    public boolean update(@Validated @RequestBody BillView bill, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(String.join(",", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
        }
        return billService.updateBill(bill);
    }

    /**
     * @summary 删除账单
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @PostMapping("/post/delete")
    public boolean delete(@RequestParam int id) {
        boolean result = billService.deleteBill(id);
        return result;
    }

    /**
     * @summary 分页查询账单
     * TODO 现在是内存分页...并且是1+N查询...真·抠脚
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/page")
    public Page<BillView> getPage(@RequestParam(defaultValue = "10") int pageSize,
                                  @RequestParam(defaultValue = "1") int pageNo) {
        return PageUtil.fillMomeryPage(billService.getAllBillViews(), pageSize, pageNo);
    }

    /**
     * @summary 分页查询账单
     * @version V2.0
     * @author Washmore
     * @since 2018/2/2
     */
    @GetMapping("/get/page/v2")
    public Page<BillView> getPageV2(@RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "1") int pageNo,
                                    @RequestParam(required = false) Integer memberId,
                                    @RequestParam(required = false) Integer typeId,
                                    @RequestParam(required = false) List<Integer> tagIds
    ) {
        return billService.getBillViewsPage(pageSize, pageNo, memberId, typeId, tagIds);
    }

    /**
     * @summary 分页查询登录人的账单
     * @version V1.0
     * @author Washmore
     * @since 2018/2/2
     */
    @GetMapping("/get/page/mine")
    public Page<BillView> getMyPage(@RequestParam(defaultValue = "10") int pageSize,
                                    @RequestParam(defaultValue = "1") int pageNo,
                                    @RequestAttribute(Constants.REQUEST_MEMBER_ID) Integer memberId,
                                    @RequestParam(required = false) Integer typeId,
                                    @RequestParam(required = false) List<Integer> tagIds
    ) {
        return billService.getBillViewsPage(pageSize, pageNo, memberId, typeId, tagIds);
    }

    /**
     * @summary 账单详情查询
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/item")
    public BillView getBillView(@RequestParam int id) {
        return billService.getBillViewById(id);
    }


    /**
     * @summary 查询所有的收支类型枚举
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/balanceTypes")
    public List getBalanceTypes() {
        return Stream.of(BalanceType.values()).map(t -> {
            Map<String, Object> balanceTypeMap = new HashMap<>();
            balanceTypeMap.put("name", t.getName());
            balanceTypeMap.put("value", t.getValue());
            return balanceTypeMap;
        }).collect(Collectors.toList());
    }
}

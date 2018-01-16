package tech.washmore.family.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import tech.washmore.family.common.enums.BalanceType;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.Page;
import tech.washmore.family.model.view.BillView;
import tech.washmore.family.service.BillService;
import tech.washmore.family.service.BilltypeService;
import tech.washmore.family.service.FamilymemberService;
import tech.washmore.family.utils.PageUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/post/add")
    public boolean add(@Validated @RequestBody Bill bill, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(String.join(",", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
        }

        return billService.addBill(bill);
    }

    @PostMapping("/post/update")
    public boolean update(@Validated @RequestBody Bill bill, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(String.join(",", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
        }
        return billService.updateBill(bill);
    }

    @PostMapping("/post/delete")
    public boolean delete(@RequestParam int id) {
        boolean result = billService.deleteBill(id);
        return result;
    }

    @GetMapping("/get/page")
    public Page<BillView> getPage(@RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int pageNo) {
        return PageUtil.fillPage(billService.getAllBillViews(), pageSize, pageNo);
    }

    @GetMapping("/get/item")
    public BillView getBillView(@RequestParam int id) {
        return billService.getBillViewById(id);
    }

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

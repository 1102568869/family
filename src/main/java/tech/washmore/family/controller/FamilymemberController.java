package tech.washmore.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.washmore.family.common.Constants;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.service.FamilymemberService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Washmore
 * @version V1.0
 * @summary 家庭成员控制器
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@RestController
@RequestMapping("/familymember")
public class FamilymemberController {
    @Autowired
    private FamilymemberService familymemberService;

    /**
     * @summary 查询第一个成员信息, 由于init.sql会初始化插入一条admin记录, 如果不做删改的话, 拿到的就是这一条
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/first")
    public Familymember getFrist() {
        return familymemberService.getFirstFamilymember();
    }

    /**
     * @summary 查询所有的成员信息
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/all")
    public List<Familymember> getAll() {
        return familymemberService.getAllFamilymembers();
    }

    /**
     * @summary 根据Id查询指定的成员信息(不包含密码)
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @GetMapping("/get/item")
    public Familymember getFamilymember(@RequestParam int id) {
        return familymemberService.getFamilymemberById(id);
    }

    /**
     * @summary 添加成员
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @PostMapping("/post/add")
    public boolean add(@Validated @RequestBody Familymember familymember, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(String.join(",", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
        }
        //验证重复
        if (familymemberService.checkExistMember(familymember)) {
            throw new IllegalArgumentException("该账号已存在!");
        }
        return familymemberService.addFamilymember(familymember);
    }

    /**
     * @summary 编辑成员
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @PostMapping("/post/update")
    public boolean update(@Validated @RequestBody Familymember familymember, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(String.join(",", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
        }
        //update 不让进行账号编辑, 在前端置灰, 在sql忽略该字段set
//        if (familymemberService.checkExistMember(familymember)) {
//            throw new IllegalArgumentException("该账号已存在!");
//        }

        return familymemberService.updateFamilymember(familymember);
    }

    /**
     * @summary 修改密码
     * @version V1.0
     * @author Washmore
     * @since 2018/1/15
     */
    @PostMapping("/post/changePassword")
    public boolean changePassword(@RequestBody Familymember familymember,
                                  @RequestAttribute(Constants.REQUEST_MEMBER_ID) int id) {
        if (familymember.getPassword() == null) {
            throw new IllegalArgumentException("请输入密码!");
        }

        familymember.setId(id);
        return familymemberService.changePassword(familymember);
    }
}

package tech.washmore.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.washmore.family.common.Constants;
import tech.washmore.family.model.Bill;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.service.FamilymemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/familymember")
public class FamilymemberController {
    @Autowired
    private FamilymemberService familymemberService;

    @GetMapping("/get/first")
    public Familymember getFrist() {
        return familymemberService.getFirstFamilymember();
    }

    @GetMapping("/get/all")
    public List<Familymember> getAll() {
        return familymemberService.getAllFamilymembers();
    }

    @GetMapping("/get/item")
    public Familymember getFamilymember(@RequestParam int id) {
        return familymemberService.getFamilymemberById(id);
    }

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

    @PostMapping("/post/changePassword")
    public boolean changePassword(@RequestBody Familymember familymember, @RequestAttribute(Constants.REQUEST_MEMBER_ID) int id) {
        if (familymember.getPassword() == null) {
            throw new IllegalArgumentException("请输入密码!");
        }

        familymember.setId(id);
        return familymemberService.changePassword(familymember);
    }
}

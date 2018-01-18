package tech.washmore.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/post/add")
    public boolean add(@Validated @RequestBody Familymember familymember, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(String.join(",", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList())));
        }
        //TODO 验证重复

        return familymemberService.addFamilymember(familymember);
    }

}

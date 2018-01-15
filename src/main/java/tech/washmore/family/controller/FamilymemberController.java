package tech.washmore.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.service.FamilymemberService;

import java.util.List;

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
}

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

@RestController
@RequestMapping("/billtype/")
public class BilltypeController {
    @Autowired
    private BilltypeService billtypeService;

    @GetMapping("/get/all")
    public List<Billtype> getAll() {
        return billtypeService.getAllBilltypes();
    }
}

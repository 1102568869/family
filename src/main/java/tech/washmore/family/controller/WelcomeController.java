package tech.washmore.family.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping({"/", ""})
    private String welcome() {
        return "/index";
    }
}

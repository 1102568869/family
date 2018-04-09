package tech.washmore.family.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Washmore
 * @version V1.0
 * @summary web前端入口
 * @Copyright (c) 2018, washmore.tech All Rights Reserved.
 * @since 2018/1/15
 */
@Controller
public class WelcomeController {

    @GetMapping({"/", ""})
    public String welcome() {
        return "/index";
    }

    @GetMapping("/v2")
    public String welcomeV2() {
        return "/v2/index";
    }
}

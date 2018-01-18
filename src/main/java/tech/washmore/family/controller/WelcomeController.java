package tech.washmore.family.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import tech.washmore.family.common.Constants;
import tech.washmore.family.common.uc.MemeryTokenManger;
import tech.washmore.family.model.Familymember;
import tech.washmore.family.utils.CookieUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WelcomeController {
    @Autowired
    private MemeryTokenManger memeryTokenManger;

    @GetMapping({"/", ""})
    public String welcome() {
        return "/index";
    }
}

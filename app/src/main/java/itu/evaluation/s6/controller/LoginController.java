package itu.evaluation.s6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/admin/login")
    public String adminLoginPage(){
        return "admin-login";
    }


    @GetMapping("/login")
    public String userLoginPage(){
        return "user-login";
    }
}

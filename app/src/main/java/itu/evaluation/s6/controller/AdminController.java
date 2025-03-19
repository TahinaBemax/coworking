package itu.evaluation.s6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/login")
    public String adminLoginPage(){
        return "login/admin-login";
    }
    @GetMapping("/dashboard")
    public String dashBoardsPage(){
        return "dashboard/dashboard";
    }
}

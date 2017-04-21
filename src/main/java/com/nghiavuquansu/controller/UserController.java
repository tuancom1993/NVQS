package com.nghiavuquansu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nghiavuquansu.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    
    @GetMapping(value="/quanlytaikhoan")
    public String showPageQuanLyNguoiDung(Model model){
        model.addAttribute("users", userService.getUsers());
        return "danhsachuser";
    }
}

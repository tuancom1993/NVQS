package com.nghiavuquansu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOutController {

    @GetMapping(value = "/thoat")
    public String doLogout(HttpSession session) {
        session.removeAttribute("loaiNghiaVus");
        return "redirect:/dangnhap";
    }
}

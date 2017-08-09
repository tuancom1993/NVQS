package com.nghiavuquansu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nghiavuquansu.common.PasswordEncoderUtils;
import com.nghiavuquansu.common.Utils;
import com.nghiavuquansu.entity.LoaiNghiaVu;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.model.JsonLoaiNghiaVu;
import com.nghiavuquansu.service.CongDanService;
import com.nghiavuquansu.service.LoaiNghiaVuService;

@Controller
public class LoginController {
    @Autowired
    CongDanService congDanService;
    @Autowired
    LoaiNghiaVuService loaiNghiaVuService;

    @RequestMapping("/dangnhap")
    public String showLoginPage() {
        System.err.println("admin: " + PasswordEncoderUtils.getPasswordEncoder().encode("admin"));
        System.err.println("user: " + PasswordEncoderUtils.getPasswordEncoder().encode("user"));
        return "login";
    }

    @RequestMapping("/loginsuccess")
    public String goHomePage(HttpSession session) {
        List<JsonLoaiNghiaVu> loaiNghiaVus = loaiNghiaVuService.getListJsonLoaiNghiaVu();
        session.setAttribute("loaiNghiaVus", loaiNghiaVus);
        return "redirect:/trangchu";
    }

    @RequestMapping("/trangchu")
    public String showHomePage(Model model) {
        User userLogin = Utils.getUserLoging();
        System.out.println("hoten: " + userLogin.getHoTen());
        model.addAttribute("listCongDan", congDanService.getAllCongDan());
        model.addAttribute("listLoaiNghiaVu", loaiNghiaVuService.getListLoaiNghiaVu());
        return "home";
    }
}

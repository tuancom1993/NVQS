package com.nghiavuquansu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nghiavuquansu.configurate.CustomUserDetail;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.model.MatKhauModel;
import com.nghiavuquansu.service.UserService;

@Controller
public class ThongTinCaNhanController {

    @Autowired 
    UserService userService;
    
    @GetMapping("/thongtincanhan")
    public String getThongTinCaNhan(Model model){
        User userLogin = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetail) {
            CustomUserDetail customUserDetails = (CustomUserDetail) principal;
            userLogin = customUserDetails.getUser();
        }
        model.addAttribute("user", userLogin);
        return "thongtincanhan";
    }
    
    @PostMapping("/thongtincanhan")
    public String suaThongTinCaNhan(Model model, @ModelAttribute User user){
        User userLogin = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetail) {
            CustomUserDetail customUserDetails = (CustomUserDetail) principal;
            userLogin = customUserDetails.getUser();
        }
        User userUpdated = userService.editUser(user, userLogin);
        model.addAttribute("user", userUpdated);
        return "thongtincanhan";
    }
    
    @GetMapping("/thongtincanhan/matkhau")
    public String showPageDoiMatKhau(Model model){
        MatKhauModel matKhauModel = new MatKhauModel();
        model.addAttribute("matKhauModel", matKhauModel);
        return "doimatkhau";
    }
    
    @PostMapping("/thongtincanhan/matkhau")
    public String doDoiMatKhau(Model model, @ModelAttribute MatKhauModel matKhauModel){
        User userLogin = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetail) {
            CustomUserDetail customUserDetails = (CustomUserDetail) principal;
            userLogin = customUserDetails.getUser();
        }
        matKhauModel = userService.doiMatKhau(matKhauModel, userLogin);
        model.addAttribute("matKhauModel", matKhauModel);
        return "doimatkhau";
    }
}

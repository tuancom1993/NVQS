package com.nghiavuquansu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nghiavuquansu.common.Utils;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.model.MatKhauModel;
import com.nghiavuquansu.service.UserService;

@Controller
public class ThongTinCaNhanController {

    @Autowired 
    UserService userService;
    
    @GetMapping("/thongtincanhan")
    public String getThongTinCaNhan(Model model){
        model.addAttribute("user", Utils.getUserLoging());
        return "thongtincanhan";
    }
    
    @PostMapping("/thongtincanhan")
    public String suaThongTinCaNhan(Model model, @ModelAttribute User user){
        User userUpdated = userService.editPersonalUserInformation(user);
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
        User userLogin = Utils.getUserLoging();
        matKhauModel = userService.doiMatKhau(matKhauModel, userLogin);
        model.addAttribute("matKhauModel", matKhauModel);
        return "doimatkhau";
    }
}

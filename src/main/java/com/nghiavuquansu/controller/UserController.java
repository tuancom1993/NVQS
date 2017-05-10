package com.nghiavuquansu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiavuquansu.common.ErrorPageUtils;
import com.nghiavuquansu.common.MessageUtils;
import com.nghiavuquansu.entity.User;
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
    
    @GetMapping(value="/quanlytaikhoan/suathongtin")
    public String showPageEditUserInformation(Model model, @RequestParam("usn") String username){
        User user = userService.getUserByUsername(username);
        
        if(user == null){
            return ErrorPageUtils.showErrorPage(model, MessageUtils.CANOT_LOAD_USER_WITH_USERNAME + username);
        }
        
        model.addAttribute("users", user);
        return "suathongtinuser";
    }
    
    @GetMapping(value="/quanlytaikhoan/themtaikhoan")
    public String showPageAddUser(User user){
        return "themuser";
    }
    
    @PostMapping(value="/quanlytaikhoan/themtaikhoan")
    public String addNewUser(Model model, @ModelAttribute User user){
        user = userService.addNewUser(user);
        
        if(user.getErrorMessage().equals(MessageUtils.ADD_NEW_USER_SUCCESSFUL))
            return "redirect:/quanlytaikhoan";
        
        model.addAttribute("user", user);
        return "themuser";
    }
}

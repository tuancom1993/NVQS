package com.nghiavuquansu.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nghiavuquansu.configurate.CustomUserDetail;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.service.CongDanService;

@Controller
public class LoginController {
	@Autowired CongDanService congDanService;

	@RequestMapping("/dangnhap")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping("/loginsuccess")
	public String goHomePage() {

		return "redirect:/trangchu";
	}

	@RequestMapping("/trangchu")
	public String showHomePage(Model model) {
		User userLogin = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof CustomUserDetail) {
			CustomUserDetail customUserDetails = (CustomUserDetail) principal;
			userLogin = customUserDetails.getUser();
		}
		System.out.println("hoten: " + userLogin.getHoten());
		model.addAttribute("listCongdan", congDanService.getAllCongDan());
		return "home";
	}
}

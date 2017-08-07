package com.nghiavuquansu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nghiavuquansu.common.PasswordEncoderUtils;
import com.nghiavuquansu.configurate.CustomUserDetail;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.service.CongDanService;
import com.nghiavuquansu.service.LoaiNghiaVuService;

@Controller
public class LoginController {
	@Autowired CongDanService congDanService;
	@Autowired LoaiNghiaVuService loaiNghiaVuService;

	@RequestMapping("/dangnhap")
	public String showLoginPage() {
		System.err.println("admin: "+PasswordEncoderUtils.getPasswordEncoder().encode("admin"));
		System.err.println("user: "+PasswordEncoderUtils.getPasswordEncoder().encode("user"));
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
		System.out.println("hoten: " + userLogin.getHoTen());
		model.addAttribute("listCongDan", congDanService.getAllCongDan());
		model.addAttribute("listLoaiNghiaVu", loaiNghiaVuService.getListLoaiNghiaVu());
		return "home";
	}
}

package com.nghiavuquansu.model;

import org.springframework.ui.Model;

public class ErrorPageUtil {
	public static String showErrorPage(Model model, String title, String content){
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		return "error";
	}
	
	public static String showErrorPage(Model model, String content){
		model.addAttribute("content", content);
		return "error";
	}
}

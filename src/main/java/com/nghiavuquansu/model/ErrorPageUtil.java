package com.nghiavuquansu.model;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;

public class ErrorPageUtil {
	public static String showErrorPage(Model model, String title, String content){
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		return "error-page";
	}
	
	public static String showErrorPage(Model model, String content){
		model.addAttribute("content", content);
		return "error-page";
	}
	
	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}
}

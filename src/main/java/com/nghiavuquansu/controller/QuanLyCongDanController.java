package com.nghiavuquansu.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nghiavuquansu.common.ErrorPageUtils;
import com.nghiavuquansu.common.MessageUtils;
import com.nghiavuquansu.configurate.CustomUserDetail;
import com.nghiavuquansu.entity.Capdaotao;
import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Loainghiavu;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.repository.LyDoRepoInterface;
import com.nghiavuquansu.service.CapDaoTaoService;
import com.nghiavuquansu.service.CongDanService;
import com.nghiavuquansu.service.LoaiNghiaVuService;

@Controller
public class QuanLyCongDanController {
	@Autowired CapDaoTaoService capDaoTaoService; 
	@Autowired LoaiNghiaVuService loaiNghiaVuService;
	@Autowired LyDoRepoInterface lyDoRepoInterface;
	@Autowired CongDanService congDanService;
	
	@RequestMapping(value="/quanlycongdan/themcongdan", method=RequestMethod.GET)
	public String showThemCongDan(Model model) throws JsonProcessingException{
		List<Capdaotao> listCapdaotao = capDaoTaoService.getListCapDaoTao();
		List<Loainghiavu> loainghiavus = loaiNghiaVuService.getListLoaiNghiaVu();
		model.addAttribute("congdan", new Congdan());
		model.addAttribute("listCapdaotao", listCapdaotao);
		model.addAttribute("listLoainghiavu", loainghiavus);
		
		return "themcongdan";
	}
	
	@RequestMapping(value="/quanlycongdan/themcongdan", method=RequestMethod.POST)
	public String doThemCongDan(@ModelAttribute Congdan congdan){
		try {
		    User userLogin = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (principal instanceof CustomUserDetail) {
	            CustomUserDetail customUserDetails = (CustomUserDetail) principal;
	            userLogin = customUserDetails.getUser();
	        }
			congdan.setCreatedBy(userLogin.getUsername());
			congdan.setCreatedDate(new Date());
			congDanService.saveCongDan(congdan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/trangchu";
	}
	
	@GetMapping(value="/quanlycongdan/suacongdan")
	public String showSuaCongDan(@RequestParam("id") int idcongdan, Model model){
		try {
			Congdan congdan = congDanService.getCongDan(idcongdan);
			if(congdan==null) return ErrorPageUtils.showErrorPage(model, MessageUtils.CANOT_LOAD_CONG_DAN_WITH_ID+idcongdan);
			List<Capdaotao> listCapdaotao = capDaoTaoService.getListCapDaoTao();
			List<Lydo> listLydo = congdan.getLydo().getLoainghiavu().getLydos();
			List<Loainghiavu> loainghiavus = loaiNghiaVuService.getListLoaiNghiaVu();
			
			model.addAttribute("congdan", congdan);
			model.addAttribute("listCapdaotao", listCapdaotao);
			model.addAttribute("listLoainghiavu", loainghiavus);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorPageUtils.showErrorPage(model, e.toString());
		}
		return "suacongdan"; 
	}
	
	@PostMapping(value="/quanlycongdan/suacongdan")
	public String doSuaCongDan(@ModelAttribute Congdan congdan, HttpServletRequest request){
		try {
		    User userLogin = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetail) {
                CustomUserDetail customUserDetails = (CustomUserDetail) principal;
                userLogin = customUserDetails.getUser();
            }
            
            congdan.setUpdatedBy(userLogin.getUsername());
            congdan.setUpdatedDate(new Date());
			congDanService.saveCongDan(congdan);
			return "redirect:/quanlycongdan/suacongdan?id="+congdan.getIdcongdan();
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/trangchu";
		}
		
	}
	
	@PostMapping(value="/quanlycongdan/xoacongdan")
	public @ResponseBody String doXoaCongDan(@RequestBody Congdan congdan){
		try {
			System.err.println("ID Congdan: "+congdan.getIdcongdan());
			congDanService.deleteCongDan(congdan.getIdcongdan());
			return "OK";
		} catch (Exception e) {
			return "NOK";
		}
	}
}

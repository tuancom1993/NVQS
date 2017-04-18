package com.nghiavuquansu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghiavuquansu.common.ErrorPageUtil;
import com.nghiavuquansu.entity.Capdaotao;
import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Loainghiavu;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.repository.CongDanRepoInterface;
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
			System.out.println(congdan.getNgaysinh().toString());
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
			if(congdan==null) return ErrorPageUtil.showErrorPage(model, "Xin lỗi chúng tôi không tìm thấy công dân với mã công dân là "+idcongdan);
			List<Capdaotao> listCapdaotao = capDaoTaoService.getListCapDaoTao();
			List<Lydo> listLydo = congdan.getLydo().getLoainghiavu().getLydos();
			List<Loainghiavu> loainghiavus = loaiNghiaVuService.getListLoaiNghiaVu();
			System.out.println("Size lydo: "+listLydo.size());
			model.addAttribute("congdan", congdan);
			model.addAttribute("listCapdaotao", listCapdaotao);
			model.addAttribute("listLoainghiavu", loainghiavus);
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorPageUtil.showErrorPage(model, e.toString());
		}
		return "suacongdan"; 
	}
	
	@PostMapping(value="/quanlycongdan/suacongdan")
	public String doSuaCongDan(@ModelAttribute Congdan congdan, HttpServletRequest request){
		try {
			System.out.println(congdan.getNgaysinh().toString());
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
			System.err.println("ID COngdan: "+congdan.getIdcongdan());
			congDanService.deleteCongDan(congdan.getIdcongdan());
			return "OK";
		} catch (Exception e) {
			return "NOK";
		}
	}
}

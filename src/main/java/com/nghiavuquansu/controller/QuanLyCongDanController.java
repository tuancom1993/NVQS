package com.nghiavuquansu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@RequestMapping(value="/themcongdan", method=RequestMethod.GET)
	public String showThemCongDan(Model model) throws JsonProcessingException{
		List<Capdaotao> listCapdaotao = capDaoTaoService.getListCapDaoTao();
		List<Loainghiavu> loainghiavus = loaiNghiaVuService.getListLoaiNghiaVu();
		ArrayList<Lydo> lydos = (ArrayList<Lydo>) lyDoRepoInterface.findAll();
		model.addAttribute("congdan", new Congdan());
		model.addAttribute("listCapdaotao", listCapdaotao);
		model.addAttribute("listLoainghiavu", loainghiavus);
		model.addAttribute("listLydo", lydos);
		
		return "themcongdan";
	}
	
	@RequestMapping(value="/themcongdan", method=RequestMethod.POST)
	public String doThemCongDan(@ModelAttribute Congdan congdan){
		try {
			System.out.println(congdan.getNgaysinh().toString());
			congDanService.saveCongDan(congdan);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/home";
	}
}

package com.nghiavuquansu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nghiavuquansu.service.LoaiNghiaVuService;

@Controller
public class LoaiNghiaVuController {
	@Autowired LoaiNghiaVuService loaiNghiaVuService;
	
	@RequestMapping(value="/getlistjsonloainghiavu")
	public @ResponseBody List<?> getListJsonLoaiNghiaVu(){
		return loaiNghiaVuService.getListJsonLoaiNghiaVu();
	}
}

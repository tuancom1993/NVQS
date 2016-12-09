package com.nghiavuquansu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghiavuquansu.service.LoaiNghiaVuService;

@Controller
public class LoaiNghiaVuController {
	@Autowired LoaiNghiaVuService loaiNghiaVuService;
	
	@RequestMapping(value="/getlistjsonloainghiavu")
	public @ResponseBody String getListJsonLoaiNghiaVu(){
		String results = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			results = mapper.writeValueAsString(loaiNghiaVuService.getListJsonLoaiNghiaVu());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println(results);
		return results;
	}
}

package com.nghiavuquansu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nghiavuquansu.service.LyDoService;

@Controller
public class LyDoController {
	@Autowired LyDoService lyDoService;
	
	@PostMapping(value = "/checklydoisexists")
	public @ResponseBody String checkLyDoIsExists (@RequestParam("idlydo") int idlydo){
		System.err.println(idlydo + "TonTai: "+lyDoService.isExists(idlydo));
		if (lyDoService.isExists(idlydo)) return "OK";
		else return "NOK";
	}
}

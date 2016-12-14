package com.nghiavuquansu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.service.CongDanService;
import com.nghiavuquansu.service.LoaiNghiaVuService;

@Controller
public class DanhSachCongDanController {
	@Autowired CongDanService congDanService;
	@Autowired LoaiNghiaVuService loaiNghiaVuService;
	
	@GetMapping("/danhsachcongdan/danhsachquatuoinghiavu")
	public String showDanhSachCongDanQuaTuoiNghiaVu(Model model){
		List<Congdan> listCongDanQuaTuoiNghiaVu = congDanService.getListCongDanQuaTuoiNghiaVu();
		model.addAttribute("listCongDanQuaTuoiNghiaVu", listCongDanQuaTuoiNghiaVu);
		return "ds-congdanquatuoinghiavu";
	}
	
	@GetMapping("/danhsachcongdan/danhsach")
	public String showDanhSachCongDanTheoLyDo(@RequestParam("id") int idlydo ,Model model){
		try {
			List<Congdan> listCongDan = congDanService.getListCongDanTheoLyDo(idlydo);
			model.addAttribute("listCongdan", listCongDan);
			model.addAttribute("listLoainghiavu", loaiNghiaVuService.getListLoaiNghiaVu());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
}

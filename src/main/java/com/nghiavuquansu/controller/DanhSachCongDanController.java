package com.nghiavuquansu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.model.ErrorPageUtil;
import com.nghiavuquansu.repository.LyDoRepoInterface;
import com.nghiavuquansu.repository.PhanLoaiLyDoRepoInterface;
import com.nghiavuquansu.service.CongDanService;
import com.nghiavuquansu.service.LoaiNghiaVuService;
import com.nghiavuquansu.service.LyDoService;
import com.nghiavuquansu.service.PhanLoaiLyDoService;

@Controller
public class DanhSachCongDanController {
	@Autowired CongDanService congDanService;
	@Autowired LoaiNghiaVuService loaiNghiaVuService;
	@Autowired LyDoService lyDoService;
	@Autowired PhanLoaiLyDoService phanLoaiLyDoService;
	
	@GetMapping("/danhsachcongdan/danhsachquatuoinghiavu/{date}/{month}/{year}")
	public String showDanhSachCongDanQuaTuoiNghiaVu(Model model, @PathVariable("date") String date, 
			@PathVariable("month") String month, @PathVariable("year") String year){
		List<Congdan> listCongDanQuaTuoiNghiaVu = new ArrayList<>();
		try {
			listCongDanQuaTuoiNghiaVu = congDanService.getListCongDanQuaTuoiNghiaVu(date, month, year);
		} catch (ParseException e) {
			e.printStackTrace();
			return ErrorPageUtil.showErrorPage(model, "Không thể tải danh sách với ngày "+date+"/"+month+"/"+year);
		}
		model.addAttribute("listCongDanQuaTuoiNghiaVu", listCongDanQuaTuoiNghiaVu);
		model.addAttribute("dateFrom", date+"/"+month+"/"+year);
		return "ds-congdanquatuoinghiavu";
	}
	
	@GetMapping("/danhsachcongdan/danhsach")
	public String showDanhSachCongDanTheoLyDo(@RequestParam("id") int idlydo, Model model){
		try {
			if(!lyDoService.isExists(idlydo)){
				return ErrorPageUtil.showErrorPage(model, "Chúng tôi không tìm thấy danh sách công dân với mã là "+idlydo);
			}
			List<Congdan> listCongDan = congDanService.getListCongDanTheoLyDo(idlydo);
			model.addAttribute("listCongdan", listCongDan);
			model.addAttribute("listLoainghiavu", loaiNghiaVuService.getListLoaiNghiaVu());
			model.addAttribute("lydoOfCongdan", lyDoService.findLyDo(idlydo));
			model.addAttribute("sizeOfListPhanloailydo", phanLoaiLyDoService.countPhanLoaiLyDoByIdLydo(idlydo));

			return "danhsachnghiavu";
		} catch (Exception e) {
			e.printStackTrace();
			return ErrorPageUtil.showErrorPage(model, ErrorPageUtil.getStackTrace(e));
		}
		
	}
}

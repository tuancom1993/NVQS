package com.nghiavuquansu.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiavuquansu.common.AgeUtils;
import com.nghiavuquansu.common.ErrorPageUtils;
import com.nghiavuquansu.common.MessageUtils;
import com.nghiavuquansu.entity.CongDan;
import com.nghiavuquansu.service.CongDanService;
import com.nghiavuquansu.service.LoaiNghiaVuService;
import com.nghiavuquansu.service.LyDoService;
import com.nghiavuquansu.service.PhanLoaiLyDoService;

@Controller
@RequestMapping("/danhsachcongdan")
public class DanhSachCongDanController {
    @Autowired
    CongDanService congDanService;
    
    @Autowired
    LoaiNghiaVuService loaiNghiaVuService;
    
    @Autowired
    LyDoService lyDoService;
    
    @Autowired
    PhanLoaiLyDoService phanLoaiLyDoService;

    @GetMapping("/quatuoinghiavu")
    public String showDanhSachCongDanQuaTuoiNghiaVu(Model model) {
        List<CongDan> listCongDanQuaTuoiNghiaVu = new ArrayList<>();
        try {
            listCongDanQuaTuoiNghiaVu = congDanService.getListCongDanQuaTuoiNghiaVu(AgeUtils.getDateCalculateAge());
        } catch (ParseException e) {
            e.printStackTrace();
            return ErrorPageUtils.showErrorPage(model,
                    MessageUtils.CANNOT_LOAD_DSCD_WITH_DATE + AgeUtils.getDateCalculateAge().toString());
        }
        model.addAttribute("listCongDanQuaTuoiNghiaVu", listCongDanQuaTuoiNghiaVu);
        model.addAttribute("dateFrom", AgeUtils.getStringFromDate(AgeUtils.getDateCalculateAge()));
        return "ds-congdanquatuoinghiavu";
    }

    @GetMapping("/danhsach")
    public String showDanhSachCongDanTheoLyDo(@RequestParam("id") int idLyDo, Model model) {
        try {
            if (!lyDoService.isExists(idLyDo)) {
                return ErrorPageUtils.showErrorPage(model,
                        MessageUtils.CANNOT_LOAD_DSCD_WITH_ID + idLyDo);
            }
            List<CongDan> listCongDan = congDanService.getListCongDanTheoLyDo(idLyDo);
            model.addAttribute("listCongDan", listCongDan);
            model.addAttribute("listLoaiNghiaVu", loaiNghiaVuService.getListLoaiNghiaVu());
            model.addAttribute("lyDoOfCongDan", lyDoService.findLyDo(idLyDo));
            model.addAttribute("sizeOfListPhanLoaiLyDo", phanLoaiLyDoService.countPhanLoaiLyDoByIdLydo(idLyDo));

            return "danhsachnghiavu";
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorPageUtils.showErrorPage(model, ErrorPageUtils.getStackTrace(e));
        }

    }
}

package com.nghiavuquansu.controller;

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
import com.nghiavuquansu.common.AgeUtils;
import com.nghiavuquansu.common.ErrorPageUtils;
import com.nghiavuquansu.common.MessageUtils;
import com.nghiavuquansu.configurate.CustomUserDetail;
import com.nghiavuquansu.entity.CapDaoTao;
import com.nghiavuquansu.entity.CongDan;
import com.nghiavuquansu.entity.LoaiNghiaVu;
import com.nghiavuquansu.entity.LyDo;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.repository.LyDoRepoInterface;
import com.nghiavuquansu.service.CapDaoTaoService;
import com.nghiavuquansu.service.CongDanService;
import com.nghiavuquansu.service.LoaiNghiaVuService;
import com.nghiavuquansu.service.PhanLoaiLyDoService;

@Controller
public class QuanLyCongDanController {
    @Autowired
    CapDaoTaoService capDaoTaoService;
    @Autowired
    LoaiNghiaVuService loaiNghiaVuService;
    @Autowired
    LyDoRepoInterface lyDoRepoInterface;
    @Autowired
    CongDanService congDanService;
    @Autowired
    PhanLoaiLyDoService phanLoaiLyDoService;

    @RequestMapping(value = "/quanlycongdan/themcongdan", method = RequestMethod.GET)
    public String showThemCongDan(Model model) throws JsonProcessingException {
        List<CapDaoTao> listCapdaotao = capDaoTaoService.getListCapDaoTao();
        List<LoaiNghiaVu> loaiNghiaVus = loaiNghiaVuService.getListLoaiNghiaVu();
        model.addAttribute("congDan", new CongDan());
        model.addAttribute("listCapDaoTao", listCapdaotao);
        model.addAttribute("listLoaiNghiaVu", loaiNghiaVus);

        return "themcongdan";
    }

    @RequestMapping(value = "/quanlycongdan/themcongdan", method = RequestMethod.POST)
    public String doThemCongDan(@ModelAttribute CongDan congDan) {
        try {
            User userLogin = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetail) {
                CustomUserDetail customUserDetails = (CustomUserDetail) principal;
                userLogin = customUserDetails.getUser();
            }
            congDan.setCreatedBy(userLogin.getUsername());
            congDan.setCreatedDate(AgeUtils.getCurrentDateInVN());
            congDanService.saveCongDan(congDan);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/trangchu";
    }

    @GetMapping(value = "/quanlycongdan/suacongdan")
    public String showSuaCongDan(@RequestParam("id") int idCongDan, Model model) {
        try {
            CongDan congDan = congDanService.getCongDan(idCongDan);
            if (congDan == null)
                return ErrorPageUtils.showErrorPage(model, MessageUtils.CANOT_LOAD_CONG_DAN_WITH_ID + idCongDan);
            List<CapDaoTao> listCapdaotao = capDaoTaoService.getListCapDaoTao();
            List<LyDo> listLydo = congDan.getLyDo().getLoaiNghiaVu().getLyDos();
            List<LoaiNghiaVu> loaiNghiaVus = loaiNghiaVuService.getListLoaiNghiaVu();

            model.addAttribute("congDan", congDan);
            model.addAttribute("listCapDaoTao", listCapdaotao);
            model.addAttribute("listLoaiNghiaVu", loaiNghiaVus);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorPageUtils.showErrorPage(model, e.toString());
        }
        return "suacongdan";
    }

    @PostMapping(value = "/quanlycongdan/suacongdan")
    public String doSuaCongDan(@ModelAttribute CongDan congDan, HttpServletRequest request) {
        try {
            User userLogin = null;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof CustomUserDetail) {
                CustomUserDetail customUserDetails = (CustomUserDetail) principal;
                userLogin = customUserDetails.getUser();
            }
            // congDanService.saveCongDan(congdan);
            congDanService.updateCongDan(congDan, userLogin);
            return "redirect:/quanlycongdan/suacongdan?id=" + congDan.getIdCongDan();
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/trangchu";
        }

    }

    @PostMapping(value = "/quanlycongdan/xoacongdan")
    public @ResponseBody String doXoaCongDan(@RequestBody CongDan congDan) {
        try {
            System.err.println("ID Congdan: " + congDan.getIdCongDan());
            congDanService.deleteCongDan(congDan.getIdCongDan());
            return "OK";
        } catch (Exception e) {
            return "NOK";
        }
    }

    @GetMapping(value = "/quanlycongdan/xemthongtincongdan")
    public String showXemThongTinCongDan(@RequestParam("id") int idCongDan, Model model) {
        try {
            CongDan congDan = congDanService.getCongDan(idCongDan);
            if (congDan == null)
                return ErrorPageUtils.showErrorPage(model, MessageUtils.CANOT_LOAD_CONG_DAN_WITH_ID + idCongDan);
            List<CapDaoTao> listCapdaotao = capDaoTaoService.getListCapDaoTao();
            List<LoaiNghiaVu> loaiNghiaVus = loaiNghiaVuService.getListLoaiNghiaVu();

            model.addAttribute("congDan", congDan);
//            model.addAttribute("listCapDaoTao", listCapdaotao);
//            model.addAttribute("listLoaiNghiaVu", loaiNghiaVus);
            model.addAttribute("phanLoaiLyDoCongDan", phanLoaiLyDoService.getPhanLoaiLyDoCongDan(congDan));
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorPageUtils.showErrorPage(model, e.toString());
        }
        return "xemcongdan";
    }
}

package com.nghiavuquansu.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nghiavuquansu.service.ExportDanhSachCongDanService;

@Controller
public class ExportDanhSachCongDanController {

    @Autowired
    ExportDanhSachCongDanService exportDanhSachCongDanService;
    
    @GetMapping("danhsachcongdan/export")
    public void exportDanhSachCongDan(HttpServletResponse response, @RequestParam("id") int idlydo){
        try {
            File exportFile = exportDanhSachCongDanService.exportDanhSachCongDan(idlydo);
            if(exportFile == null) throw new Exception("Cannot export file");
            String fileName = exportFile.getName();
            response.setContentType("application/octet-stream; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            IOUtils.copy(new FileInputStream(exportFile), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

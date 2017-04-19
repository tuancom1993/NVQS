package com.nghiavuquansu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Loainghiavu;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.entity.Phanloailydo;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class ExportDanhSachCongDanService {
    
    @Autowired 
    LyDoRepoInterface lyDoRepoInterface;
    
    @Autowired
    CongDanService congDanService;
    
    private Workbook workbook;
    
    public File exportDanhSachCongDan(int idlydo){
        try {
            Lydo lydo = lyDoRepoInterface.findOne(idlydo);
            if(lydo == null) throw new Exception("Cannot find LyDo by id = "+idlydo);
            
            Loainghiavu loainghiavu = lydo.getLoainghiavu();
            List<Phanloailydo> phanloailydos = lydo.getPhanloailydos();
            List<Congdan> congdans = congDanService.getListCongDanTheoLyDo(idlydo); 
            
            String pathTemp = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
            File fileExport = new File(pathTemp+"/"+getFileName(lydo));
            
            Workbook workbook = new XSSFWorkbook();
            this.workbook = workbook;
            setWorkBookData(workbook, loainghiavu, lydo, phanloailydos ,congdans);
            
            FileOutputStream fileOutputStream = new FileOutputStream(fileExport);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            System.out.println("File done...!");
            
            return fileExport;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileName(Lydo lydo){
        String fileName = lydo.getMota();
        fileName = fileName.length() > 220 ? fileName.substring(0, 220) : fileName;
        fileName = stripAccents(fileName);
        fileName +=".xlsx";
        return fileName;
    }
    
    public static String stripAccents(String s) 
    {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
    
    private void setWorkBookData(Workbook workbook, Loainghiavu loainghiavu, Lydo lydo,
            List<Phanloailydo> phanloailydos, List<Congdan> congdans) {
        Sheet sheet = workbook.createSheet("Danh Sach Cong Dan");
        sheet.getPrintSetup().setLandscape(true);
        setThongTinNghiaVuForSheet(sheet, loainghiavu, lydo);
        setDanhSachCongDanForSheet(sheet, loainghiavu, lydo, phanloailydos, congdans);
    }

    private void setThongTinNghiaVuForSheet(Sheet sheet, Loainghiavu loainghiavu, Lydo lydo) {
        Row rowLoaiNghiaVu = sheet.createRow(1);
        rowLoaiNghiaVu.createCell(0).setCellValue(loainghiavu.getMota());
        
    }

    private void setDanhSachCongDanForSheet(Sheet sheet, Loainghiavu loainghiavu, Lydo lydo,
            List<Phanloailydo> phanloailydos, List<Congdan> congdans) {
        int rowIndex = 10;
        
    }

}

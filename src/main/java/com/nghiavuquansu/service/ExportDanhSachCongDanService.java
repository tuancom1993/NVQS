package com.nghiavuquansu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.util.List;

import javax.jws.soap.SOAPBinding.Style;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.AgeUtil;
import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Loainghiavu;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.entity.Phanloailydo;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class ExportDanhSachCongDanService {
    
    public static final String PHUONG = "Thach Thang";
    
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
        String fileName = "Danh Sach " +  lydo.getDanhsach();
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
        
        sheet.setColumnWidth(0, 1200);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 1700);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 2000);
        sheet.setColumnWidth(6, 5000);
        sheet.setColumnWidth(7, 7000);
        
        setHeaderForSheet(sheet, lydo);
        setThongTinNghiaVuForSheet(sheet, loainghiavu, lydo);
        setDanhSachCongDanForSheet(sheet, loainghiavu, lydo, phanloailydos, congdans);
    }

    private void setHeaderForSheet(Sheet sheet, Lydo lydo) {
        Row rowHeader = sheet.createRow(0);
        
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        Cell cell_1 = rowHeader.createCell(0);
        cell_1.setCellValue("HOI DONG NVQS");
        cell_1.setCellStyle(getCellStyleForHeader());
        
        Cell cell_2 = rowHeader.createCell(7);
        cell_2.setCellValue("Danh sach "+lydo.getDanhsach());
        cell_2.setCellStyle(getCellStyleDanhSach());
        
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
        rowHeader = sheet.createRow(1);
        Cell cell_3 = rowHeader.createCell(0);
        cell_3.setCellValue("PHUONG THACH THANG");
        cell_3.setCellStyle(getCellStyleForHeader());
    }

    private void setThongTinNghiaVuForSheet(Sheet sheet, Loainghiavu loainghiavu, Lydo lydo) {
        
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 8));
        Row rowLoaiNghiaVu = sheet.createRow(3);
        rowLoaiNghiaVu.createCell(0).setCellValue("DANH SACH");
        rowLoaiNghiaVu.getCell(0).setCellStyle(getCellStyleForHeader());
        
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));
        rowLoaiNghiaVu = sheet.createRow(4);
        rowLoaiNghiaVu.createCell(0).setCellValue(loainghiavu.getMota());
        rowLoaiNghiaVu.getCell(0).setCellStyle(getCellStyleForHeader());
        
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 8));
        rowLoaiNghiaVu = sheet.createRow(5);
        rowLoaiNghiaVu.createCell(0).setCellValue(lydo.getMota());
        rowLoaiNghiaVu.getCell(0).setCellStyle(getCellStyleLyDo());
    }

    private void setDanhSachCongDanForSheet(Sheet sheet, Loainghiavu loainghiavu, Lydo lydo,
            List<Phanloailydo> phanloailydos, List<Congdan> congdans) {
        int rowIndex = 7;
        rowIndex = setTableHeader(sheet, rowIndex);
        rowIndex = setTableContent(sheet, rowIndex, lydo, phanloailydos, congdans);
    }

    private int setTableHeader(Sheet sheet, int rowIndex) {
        Row tableHeader = sheet.createRow(rowIndex);
        
        tableHeader.createCell(0).setCellValue("STT");
        tableHeader.getCell(0).setCellStyle(getCellStyleForTableHeader());
        
        tableHeader.createCell(1).setCellValue("Ho va Ten");
        tableHeader.getCell(1).setCellStyle(getCellStyleForTableHeader());
        
        tableHeader.createCell(2).setCellValue("Ngay, thang nam sinh");
        tableHeader.getCell(2).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(3).setCellValue("TDP (thon)");
        tableHeader.getCell(3).setCellStyle(getCellStyleForTableHeader());
        
        tableHeader.createCell(4).setCellValue("Phuong (xa)");
        tableHeader.getCell(4).setCellStyle(getCellStyleForTableHeader());
        
        tableHeader.createCell(5).setCellValue("Trinh do hoc van");
        tableHeader.getCell(5).setCellStyle(getCellStyleForTableHeader());
        
        tableHeader.createCell(6).setCellValue("Ho ten cha (me)");
        tableHeader.getCell(6).setCellStyle(getCellStyleForTableHeader());
        
        tableHeader.createCell(7).setCellValue("Ghi chu");
        tableHeader.getCell(7).setCellStyle(getCellStyleForTableHeader());
        
        rowIndex++;
        return rowIndex;
    }
    
    
    private int setTableContent(Sheet sheet, int rowIndex, Lydo lydo, List<Phanloailydo> phanloailydos, List<Congdan> congdans) {
        int stt = 1;
        if(phanloailydos.isEmpty()){
            for(Congdan congdan : congdans){
                Row row = sheet.createRow(rowIndex);
                
                row.createCell(0).setCellValue(stt++);
                row.getCell(0).setCellStyle(getCellStyleForTableContent());
                
                row.createCell(1).setCellValue(congdan.getHoten());
                row.getCell(1).setCellStyle(getCellStyleForTableContent());
                
                row.createCell(2).setCellValue(AgeUtil.getStringFromDate(congdan.getNgaysinh()));
                row.getCell(2).setCellStyle(getCellStyleForTableContent());
                
                row.createCell(3).setCellValue(congdan.getTodanpho());
                row.getCell(3).setCellStyle(getCellStyleForTableContent());
                
                row.createCell(4).setCellValue(this.PHUONG);
                row.getCell(4).setCellStyle(getCellStyleForTableContent());
                
                row.createCell(5).setCellValue(congdan.getTrinhdohocvan());
                row.getCell(5).setCellStyle(getCellStyleForTableContent());
                
                String hoTenChaMe = congdan.getHotencha();
                if(congdan.getHotencha() == null || "".equals(congdan.getHotencha())) hoTenChaMe = "" + congdan.getHotenme();
                row.createCell(6).setCellValue(hoTenChaMe);
                row.getCell(6).setCellStyle(getCellStyleForTableContent());
                
                row.createCell(7).setCellValue(congdan.getGhichu());
                row.getCell(7).setCellStyle(getCellStyleForTableContent());
                
                rowIndex++;
            }
        } else {
            int i = 1;
            for (Phanloailydo phanloailydo : phanloailydos){
                CellRangeAddress ranger = new CellRangeAddress(rowIndex, rowIndex, 0, 7);
                sheet.addMergedRegion(ranger);
                
                Row rowPLDL = sheet.createRow(rowIndex);
                rowPLDL.createCell(0).setCellValue(new StringBuffer().append("         ").append(i).append(". ").append(phanloailydo.getMota()).toString());
                rowPLDL.getCell(0).setCellStyle(getCellStylePLLD());

                RegionUtil.setBorderTop(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);
                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);
                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);
                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);

                i++;
                rowIndex++;
                for(Congdan congdan : congdans){
                    if(congdan.getIdphanloailydo() != phanloailydo.getIdphanloailydo())
                        continue;
                    
                    Row row = sheet.createRow(rowIndex);
                    
                    row.createCell(0).setCellValue(stt++);
                    row.getCell(0).setCellStyle(getCellStyleForTableContent());
                    
                    row.createCell(1).setCellValue(congdan.getHoten());
                    row.getCell(1).setCellStyle(getCellStyleForTableContent());
                    
                    row.createCell(2).setCellValue(AgeUtil.getStringFromDate(congdan.getNgaysinh()));
                    row.getCell(2).setCellStyle(getCellStyleForTableContent());
                    
                    row.createCell(3).setCellValue(congdan.getTodanpho());
                    row.getCell(3).setCellStyle(getCellStyleForTableContent());
                    
                    row.createCell(4).setCellValue(this.PHUONG);
                    row.getCell(4).setCellStyle(getCellStyleForTableContent());
                    
                    row.createCell(5).setCellValue(congdan.getTrinhdohocvan());
                    row.getCell(5).setCellStyle(getCellStyleForTableContent());
                    
                    StringBuffer hoTenChaMe = new StringBuffer(congdan.getHotencha());
                    if(hoTenChaMe.toString() == null || "".equals(hoTenChaMe.toString()))
                        hoTenChaMe.append(congdan.getHotenme());
                    row.createCell(6).setCellValue(hoTenChaMe.toString());
                    row.getCell(6).setCellStyle(getCellStyleForTableContent());
                    
                    row.createCell(7).setCellValue(congdan.getGhichu());
                    row.getCell(7).setCellStyle(getCellStyleForTableContent());
                    
                    rowIndex++;
                }
            }
        }
        
        
        return rowIndex;
    }

    private CellStyle getCellStyleWrap(boolean isBold){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(isBold);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }
    
    private CellStyle getCellStyleWrap(){
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        return style;
    }
    
    private CellStyle getCellStyleForHeader(){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short)(15*20));
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
    }
    
    private CellStyle getCellStyleDanhSach(){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setItalic(true);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        return style;
    }
    
    private CellStyle getCellStyleLyDo(){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setItalic(true);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
    }
    
    private CellStyle getCellStyleForTableHeader(){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        return style;
    }
    
    private CellStyle getCellStyleForTableContent(){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        style.setFont(font);
        style.setWrapText(true);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        return style;
    }
    
    private CellStyle getCellStylePLLD(){
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

}

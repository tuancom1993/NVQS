package com.nghiavuquansu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.AgeUtils;
import com.nghiavuquansu.entity.CongDan;
import com.nghiavuquansu.entity.LoaiNghiaVu;
import com.nghiavuquansu.entity.LyDo;
import com.nghiavuquansu.entity.PhanLoaiLyDo;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class ExportDanhSachCongDanService {

    public static final String PHUONG = "Thạch Thang";

    @Autowired
    LyDoRepoInterface lyDoRepoInterface;

    @Autowired
    CongDanService congDanService;

    private Workbook workbook;

    public File exportDanhSachCongDan(int idLyDo) {
        try {
            LyDo lyDo = lyDoRepoInterface.findOne(idLyDo);
            if (lyDo == null)
                throw new Exception("Cannot find LyDo by id = " + idLyDo);

            LoaiNghiaVu loaiNghiaVu = lyDo.getLoaiNghiaVu();
            List<PhanLoaiLyDo> phanLoaiLyDos = lyDo.getPhanLoaiLyDos();
            List<CongDan> congDans = congDanService.getListCongDanTheoLyDo(idLyDo);

            String pathTemp = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
            File fileExport = new File(pathTemp + "/" + getFileName(lyDo));

            Workbook workbook = new XSSFWorkbook();
            this.workbook = workbook;
            setWorkBookData(workbook, loaiNghiaVu, lyDo, phanLoaiLyDos, congDans);

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

    private String getFileName(LyDo lyDo) {
        String fileName = "Danh Sach " + lyDo.getDanhSach();
        fileName = fileName.length() > 220 ? fileName.substring(0, 220) : fileName;
        fileName = stripAccents(fileName);
        fileName += ".xlsx";
        return fileName;
    }

    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    private void setWorkBookData(Workbook workbook, LoaiNghiaVu loaiNghiaVu, LyDo lyDo,
            List<PhanLoaiLyDo> phanLoaiLyDos, List<CongDan> congDans) {
        Sheet sheet = workbook.createSheet("Danh Sach Cong Dan");
        sheet.getPrintSetup().setLandscape(true);
        sheet.setFitToPage(true);

        sheet.setColumnWidth(0, 1200);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 1700);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 2000);
        sheet.setColumnWidth(6, 5000);
        sheet.setColumnWidth(7, 7000);

        setHeaderForSheet(sheet, lyDo);
        setThongTinNghiaVuForSheet(sheet, loaiNghiaVu, lyDo);
        setDanhSachCongDanForSheet(sheet, loaiNghiaVu, lyDo, phanLoaiLyDos, congDans);
    }

    private void setHeaderForSheet(Sheet sheet, LyDo lyDo) {
        Row rowHeader = sheet.createRow(0);

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
        Cell cell_1 = rowHeader.createCell(0);
        cell_1.setCellValue("HỘI ĐỒNG NVQS");
        cell_1.setCellStyle(getCellStyleForHeader());

        Cell cell_2 = rowHeader.createCell(7);
        cell_2.setCellValue("Danh sách " + lyDo.getDanhSach());
        cell_2.setCellStyle(getCellStyleDanhSach());

        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
        rowHeader = sheet.createRow(1);
        Cell cell_3 = rowHeader.createCell(0);
        cell_3.setCellValue("PHƯỜNG " + this.PHUONG.toUpperCase());
        cell_3.setCellStyle(getCellStyleForHeader());
    }

    private void setThongTinNghiaVuForSheet(Sheet sheet, LoaiNghiaVu loaiNghiaVu, LyDo lyDo) {

        sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 8));
        Row rowLoaiNghiaVu = sheet.createRow(3);
        rowLoaiNghiaVu.createCell(0).setCellValue("DANH SÁCH");
        rowLoaiNghiaVu.getCell(0).setCellStyle(getCellStyleForHeader());

        sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 8));
        rowLoaiNghiaVu = sheet.createRow(4);
        rowLoaiNghiaVu.createCell(0).setCellValue(loaiNghiaVu.getMoTa());
        rowLoaiNghiaVu.getCell(0).setCellStyle(getCellStyleForHeader());

        sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 8));
        rowLoaiNghiaVu = sheet.createRow(5);
        rowLoaiNghiaVu.createCell(0).setCellValue(lyDo.getMoTa());
        rowLoaiNghiaVu.getCell(0).setCellStyle(getCellStyleLyDo());
    }

    private void setDanhSachCongDanForSheet(Sheet sheet, LoaiNghiaVu loaiNghiaVu, LyDo lyDo,
            List<PhanLoaiLyDo> phanLoaiLyDos, List<CongDan> congDans) {
        int rowIndex = 7;
        rowIndex = setTableHeader(sheet, rowIndex);
        rowIndex = setTableContent(sheet, rowIndex, lyDo, phanLoaiLyDos, congDans);
    }

    private int setTableHeader(Sheet sheet, int rowIndex) {
        Row tableHeader = sheet.createRow(rowIndex);

        tableHeader.createCell(0).setCellValue("STT");
        tableHeader.getCell(0).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(1).setCellValue("Họ và Tên");
        tableHeader.getCell(1).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(2).setCellValue("Ngày, tháng năm sinh");
        tableHeader.getCell(2).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(3).setCellValue("TDP (thôn)");
        tableHeader.getCell(3).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(4).setCellValue("Phường (xã)");
        tableHeader.getCell(4).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(5).setCellValue("Trình độ học vấn");
        tableHeader.getCell(5).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(6).setCellValue("Họ tên cha (mẹ)");
        tableHeader.getCell(6).setCellStyle(getCellStyleForTableHeader());

        tableHeader.createCell(7).setCellValue("Ghi chú");
        tableHeader.getCell(7).setCellStyle(getCellStyleForTableHeader());

        rowIndex++;
        return rowIndex;
    }

    private int setTableContent(Sheet sheet, int rowIndex, LyDo lyDo, List<PhanLoaiLyDo> phanLoaiLyDos,
            List<CongDan> congDans) {
        int stt = 1;
        if (phanLoaiLyDos.isEmpty()) {
            for (CongDan congDan : congDans) {
                Row row = sheet.createRow(rowIndex);
                setCongdanDetailInTableContent(congDan, stt, row);
                stt++;
                rowIndex++;
            }
        } else {
            int i = 1;
            for (PhanLoaiLyDo phanLoaiLyDo : phanLoaiLyDos) {
                CellRangeAddress ranger = new CellRangeAddress(rowIndex, rowIndex, 0, 7);
                sheet.addMergedRegion(ranger);

                Row rowPLDL = sheet.createRow(rowIndex);
                rowPLDL.createCell(0).setCellValue(new StringBuffer().append("         ").append(i).append(". ")
                        .append(phanLoaiLyDo.getMoTa()).toString());
                rowPLDL.getCell(0).setCellStyle(getCellStylePLLD());

                RegionUtil.setBorderTop(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);
                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);
                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);
                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, ranger, sheet, this.workbook);

                i++;
                rowIndex++;
                for (CongDan congDan : congDans) {
                    if (congDan.getIdPhanLoaiLyDo() != phanLoaiLyDo.getIdPhanLoaiLyDo())
                        continue;

                    Row row = sheet.createRow(rowIndex);
                    setCongdanDetailInTableContent(congDan, stt, row);
                    stt++;
                    rowIndex++;
                }
            }
        }
        return rowIndex;
    }

    private void setCongdanDetailInTableContent(CongDan congDan, int stt, Row row) {
        row.createCell(0).setCellValue(stt);
        row.getCell(0).setCellStyle(getCellStyleForTableContent());

        row.createCell(1).setCellValue(congDan.getHoTen());
        row.getCell(1).setCellStyle(getCellStyleForTableContent());

        row.createCell(2).setCellValue(AgeUtils.getStringFromDate(congDan.getNgaySinh()));
        row.getCell(2).setCellStyle(getCellStyleForTableContent());

        row.createCell(3).setCellValue(congDan.getToDanPho());
        row.getCell(3).setCellStyle(getCellStyleForTableContent());

        row.createCell(4).setCellValue(this.PHUONG);
        row.getCell(4).setCellStyle(getCellStyleForTableContent());

        row.createCell(5).setCellValue(congDan.getTrinhDoHocVan());
        row.getCell(5).setCellStyle(getCellStyleForTableContent());

        StringBuffer hoTenChaMe = new StringBuffer(congDan.getHoTenCha());
        if (hoTenChaMe.toString() == null || "".equals(hoTenChaMe.toString()))
            hoTenChaMe.append(congDan.getHoTenMe());
        row.createCell(6).setCellValue(hoTenChaMe.toString());
        row.getCell(6).setCellStyle(getCellStyleForTableContent());

        row.createCell(7).setCellValue(congDan.getGhiChu());
        row.getCell(7).setCellStyle(getCellStyleForTableContent());
    }

    private CellStyle getCellStyleWrap(boolean isBold) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(isBold);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

    private CellStyle getCellStyleWrap() {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        return style;
    }

    private CellStyle getCellStyleForHeader() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) (15 * 20));
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
    }

    private CellStyle getCellStyleDanhSach() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setItalic(true);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        return style;
    }

    private CellStyle getCellStyleLyDo() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setItalic(true);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
    }

    private CellStyle getCellStyleForTableHeader() {
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

    private CellStyle getCellStyleForTableContent() {
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

    private CellStyle getCellStylePLLD() {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setWrapText(true);
        return style;
    }

}

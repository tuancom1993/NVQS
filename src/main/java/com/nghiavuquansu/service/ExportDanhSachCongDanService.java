package com.nghiavuquansu.service;

import java.io.File;
import java.text.Normalizer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class ExportDanhSachCongDanService {
    
    @Autowired 
    LyDoRepoInterface lyDoRepoInterface;
    
    @Autowired
    CongDanService congDanService;
    
    public File exportDanhSachCongDan(int idlydo){
        try {
            Lydo lydo = lyDoRepoInterface.findOne(idlydo);
            if(lydo == null) throw new Exception("Cannot find LyDo by id = "+idlydo);
            
            List<Congdan> congdans = congDanService.getListCongDanTheoLyDo(idlydo); 
            
            String pathTemp = new File(System.getProperty("java.io.tmpdir")).getAbsolutePath();
            File fileExport = new File(pathTemp+"/"+getFileName(lydo));
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
}

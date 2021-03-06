package com.nghiavuquansu.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.Constants;
import com.nghiavuquansu.common.DateUtils;
import com.nghiavuquansu.common.Utils;
import com.nghiavuquansu.entity.CongDan;
import com.nghiavuquansu.entity.LyDo;
import com.nghiavuquansu.entity.PhanLoaiLyDo;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.repository.CongDanRepoInterface;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class CongDanService {

    @Autowired
    CongDanRepoInterface congDanRepoInterface;

    @Autowired
    LyDoRepoInterface lyDoRepoInterface;

    public void saveCongDan(CongDan congDan) throws Exception {
        User userLogin = Utils.getUserLoging();
        congDan.setCreatedBy(userLogin.getUsername());
        congDan.setCreatedDate(DateUtils.getCurrentDateInVN());
        congDan.setPhuong(Constants.PHUONG);
        congDanRepoInterface.save(congDan);
    }

    public ArrayList<CongDan> getAllCongDan() {
        return (ArrayList<CongDan>) congDanRepoInterface.findAll();
    }

    public CongDan getCongDan(int idcongdan) throws Exception {
        return congDanRepoInterface.findOne(idcongdan);
    }

    public List<CongDan> getListCongDanQuaTuoiNghiaVu(Date dateFrom) throws ParseException {
        ArrayList<CongDan> congDans = (ArrayList<CongDan>) congDanRepoInterface.findAll();
        ArrayList<CongDan> congDanQuaTuoiNghiaVus = new ArrayList<>();
        for (CongDan congDan : congDans) {
            if (isQuaTuoiNghiaVu(congDan, dateFrom))
                congDanQuaTuoiNghiaVus.add(congDan);
        }
        return congDanQuaTuoiNghiaVus;
    }

    public void deleteCongDan(int idCongDan) throws Exception {
        congDanRepoInterface.delete(idCongDan);
    }

    public List<CongDan> getListCongDanTheoLyDo(int idLyDo) throws Exception {
        LyDo lyDo = lyDoRepoInterface.findOne(idLyDo);
        lyDo.setIdLyDo(idLyDo);
        List<CongDan> list = congDanRepoInterface.findByLyDo(lyDo);
        Iterator<CongDan> it = list.iterator();
        while (it.hasNext()) {
            CongDan congDan = it.next();
            if (isQuaTuoiNghiaVu(congDan, DateUtils.getDateCalculateAge()))
                it.remove();
        }
        List<PhanLoaiLyDo> phanLoaiLyDos = lyDo.getPhanLoaiLyDos(); 
        if (phanLoaiLyDos.size() > 0)
            setIndexForListCongDan(phanLoaiLyDos, list);

        return list;
    }


    private void setIndexForListCongDan(List<PhanLoaiLyDo> phanLoaiLyDos, List<CongDan> congDans) {
        for(PhanLoaiLyDo phanLoaiLyDo : phanLoaiLyDos){
            int index = 0;
            for(CongDan congDan : congDans)
                if(phanLoaiLyDo.getIdPhanLoaiLyDo() == congDan.getIdPhanLoaiLyDo())
                    congDan.setIndex(++index);
        }
    }

    private boolean isQuaTuoiNghiaVu(CongDan congDan, Date dateFrom) {
        int age = DateUtils.getAge(dateFrom, congDan.getNgaySinh());
        int idCapDaoTao = congDan.getCapDaoTao().getIdCapDaoTao();

        if ((idCapDaoTao == 3 || idCapDaoTao == 4) && age > 27) {
            return true;
        } else if ((idCapDaoTao != 4 && idCapDaoTao != 3) && age > 25) {
            return true;
        } else
            return false;
    }

    public void updateCongDan(CongDan congDan) {
        User userLogin = Utils.getUserLoging();
        
        congDan.setUpdatedBy(userLogin.getUsername());
        congDan.setUpdatedDate(DateUtils.getCurrentDateInVN());

        CongDan congDanDB = congDanRepoInterface.findOne(congDan.getIdCongDan());
        congDan.setCreatedBy(congDanDB.getCreatedBy());
        congDan.setCreatedDate(congDanDB.getCreatedDate());

        congDanRepoInterface.save(congDan);
    }
}

package com.nghiavuquansu.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.AgeUtils;
import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.entity.Phanloailydo;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.repository.CongDanRepoInterface;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class CongDanService {

    @Autowired
    CongDanRepoInterface congDanRepoInterface;

    @Autowired
    LyDoRepoInterface lyDoRepoInterface;

    public void saveCongDan(Congdan congdan) throws Exception {
        congDanRepoInterface.save(congdan);
    }

    public ArrayList<Congdan> getAllCongDan() {
        return (ArrayList<Congdan>) congDanRepoInterface.findAll();
    }

    public Congdan getCongDan(int idcongdan) throws Exception {
        return congDanRepoInterface.findOne(idcongdan);
    }

    public List<Congdan> getListCongDanQuaTuoiNghiaVu(Date dateFrom) throws ParseException {
        ArrayList<Congdan> congdans = (ArrayList<Congdan>) congDanRepoInterface.findAll();
        ArrayList<Congdan> congDanQuaTuoiNghiaVus = new ArrayList<>();
        for (Congdan congdan : congdans) {
            int idcapdaotao = congdan.getCapdaotao().getIdcapdaotao();
            Date ngaysinh = congdan.getNgaysinh();
            int age = 0;
            // if ("0".equals(date) && "0".equals(month) && "0".equals(year)){
            // age = AgeUtil.getAge(ngaysinh);
            // } else {
            // String dateString = date+"/"+month+"/"+year;
            // DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            // Date dateFrom = dateFormat.parse(dateString);
            // age = AgeUtil.getAge(dateFrom, ngaysinh);
            // }

            age = AgeUtils.getAge(dateFrom, ngaysinh);

            // if((idcapdaotao == 3 || idcapdaotao == 4) && age > 27){
            // congDanQuaTuoiNghiaVus.add(congdan);
            // } else if ((idcapdaotao != 4 && idcapdaotao != 3) && age > 25 ){
            // congDanQuaTuoiNghiaVus.add(congdan);
            // }
            if (isQuaTuoiNghiaVu(congdan, dateFrom))
                congDanQuaTuoiNghiaVus.add(congdan);
        }
        return congDanQuaTuoiNghiaVus;
    }

    public void deleteCongDan(int idcongdan) throws Exception {
        congDanRepoInterface.delete(idcongdan);
    }

    public List<Congdan> getListCongDanTheoLyDo(int idlydo) throws Exception {
        Lydo lydo = lyDoRepoInterface.findOne(idlydo);
        lydo.setIdlydo(idlydo);
        List<Congdan> list = congDanRepoInterface.findByLydo(lydo);
        Iterator<Congdan> it = list.iterator();
        while (it.hasNext()) {
            Congdan congdan = it.next();
            if (isQuaTuoiNghiaVu(congdan, AgeUtils.getDateCalculateAge()))
                it.remove();
        }
        List<Phanloailydo> phanloailydos = lydo.getPhanloailydos(); 
        if (phanloailydos.size() > 0)
            setIndexForListCongDan(phanloailydos, list);

        return list;
    }


    private void setIndexForListCongDan(List<Phanloailydo> phanloailydos, List<Congdan> congdans) {
        for(Phanloailydo phanloailydo : phanloailydos){
            int index = 0;
            for(Congdan congdan : congdans)
                if(phanloailydo.getIdphanloailydo() == congdan.getIdphanloailydo())
                    congdan.setIndex(++index);
        }
    }

    private boolean isQuaTuoiNghiaVu(Congdan congdan, Date dateFrom) {
        int age = AgeUtils.getAge(dateFrom, congdan.getNgaysinh());
        int idcapdaotao = congdan.getCapdaotao().getIdcapdaotao();

        if ((idcapdaotao == 3 || idcapdaotao == 4) && age > 27) {
            return true;
        } else if ((idcapdaotao != 4 && idcapdaotao != 3) && age > 25) {
            return true;
        } else
            return false;
    }

    public void updateCongDan(Congdan congdan, User userLogin) {
        congdan.setUpdatedBy(userLogin.getUsername());
        congdan.setUpdatedDate(AgeUtils.getCurrentDateInVN());

        Congdan congDanDB = congDanRepoInterface.findOne(congdan.getIdcongdan());
        congdan.setCreatedBy(congDanDB.getCreatedBy());
        congdan.setCreatedDate(congDanDB.getCreatedDate());

        congDanRepoInterface.save(congdan);
    }
}

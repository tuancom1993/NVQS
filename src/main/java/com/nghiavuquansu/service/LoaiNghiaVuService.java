package com.nghiavuquansu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.LoaiNghiaVu;
import com.nghiavuquansu.entity.LyDo;
import com.nghiavuquansu.entity.PhanLoaiLyDo;
import com.nghiavuquansu.model.JsonLoaiNghiaVu;
import com.nghiavuquansu.model.JsonLyDo;
import com.nghiavuquansu.model.JsonPhanLoaiLyDo;
import com.nghiavuquansu.repository.LoaiNghiaVuRepoInterface;
import com.nghiavuquansu.repository.LyDoRepoInterface;
import com.nghiavuquansu.repository.PhanLoaiLyDoRepoInterface;

@Service
public class LoaiNghiaVuService {
    @Autowired
    LoaiNghiaVuRepoInterface loaiNghiaVuRepoInterface;
    @Autowired
    LyDoRepoInterface lyDoRepoInterface;
    @Autowired
    PhanLoaiLyDoRepoInterface phanLoaiLyDoRepoInterface;

    public List<LoaiNghiaVu> getListLoaiNghiaVu() {
        ArrayList<LoaiNghiaVu> listLoaiNgiaVu = (ArrayList<LoaiNghiaVu>) loaiNghiaVuRepoInterface.findAll();
        return listLoaiNgiaVu;
    }

    private ArrayList<LyDo> getListLyDoByLoaiNghiaVu(LoaiNghiaVu loaiNghiaVu) {
        ArrayList<LyDo> lyDos = lyDoRepoInterface.findLyDoByLoaiNghiaVu(loaiNghiaVu);
        for (LyDo lydo : lyDos) {
            ArrayList<PhanLoaiLyDo> phanLoaiLyDos = getListPhanLoaiLyDoByLyDo(lydo);
            lydo.setPhanLoaiLyDos(phanLoaiLyDos);
        }
        return lyDos;
    }

    private ArrayList<PhanLoaiLyDo> getListPhanLoaiLyDoByLyDo(LyDo lyDo) {
        return phanLoaiLyDoRepoInterface.findPhanLoaiLyDoByLyDo(lyDo);
    }

    public ArrayList<JsonLoaiNghiaVu> getListJsonLoaiNghiaVu() {
        ArrayList<LoaiNghiaVu> listLoaiNgiaVu = (ArrayList<LoaiNghiaVu>) getListLoaiNghiaVu();
        ArrayList<JsonLoaiNghiaVu> listJsonLoaiNghiaVu = new ArrayList<>();
        for (LoaiNghiaVu loaiNghiaVu : listLoaiNgiaVu) {
            JsonLoaiNghiaVu jsonLoaiNghiaVu = new JsonLoaiNghiaVu(loaiNghiaVu.getIdLoaiNghiaVu(), loaiNghiaVu.getMoTa(),
                    getListJsonLyDo(loaiNghiaVu.getLyDos()));
            listJsonLoaiNghiaVu.add(jsonLoaiNghiaVu);
        }
        return listJsonLoaiNghiaVu;
    }

    private ArrayList<JsonLyDo> getListJsonLyDo(List<LyDo> listLyDo) {
        ArrayList<JsonLyDo> listJsonLyDo = new ArrayList<>();
        for (LyDo lydo : listLyDo) {
            JsonLyDo jsonLyDo = new JsonLyDo(lydo.getIdLyDo(), lydo.getMoTa(), lydo.getDanhSach(),
                    getListJsonPhanLoaiLyDo(lydo.getPhanLoaiLyDos()));
            listJsonLyDo.add(jsonLyDo);
        }
        return listJsonLyDo;
    }

    private ArrayList<JsonPhanLoaiLyDo> getListJsonPhanLoaiLyDo(List<PhanLoaiLyDo> listPhanLoaiLyDo) {
        if (listPhanLoaiLyDo == null || listPhanLoaiLyDo.isEmpty()) {
            return new ArrayList<>();
        } else {
            ArrayList<JsonPhanLoaiLyDo> listJsonPhanLoaiLyDo = new ArrayList<>();
            for (PhanLoaiLyDo phanloailydo : listPhanLoaiLyDo) {
                JsonPhanLoaiLyDo jsonPhanLoaiLyDo = new JsonPhanLoaiLyDo(phanloailydo.getIdPhanLoaiLyDo(),
                        phanloailydo.getMoTa());
                listJsonPhanLoaiLyDo.add(jsonPhanLoaiLyDo);
            }
            return listJsonPhanLoaiLyDo;
        }

    }

}

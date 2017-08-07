package com.nghiavuquansu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.CongDan;
import com.nghiavuquansu.entity.LyDo;
import com.nghiavuquansu.entity.PhanLoaiLyDo;
import com.nghiavuquansu.repository.PhanLoaiLyDoRepoInterface;

@Service
public class PhanLoaiLyDoService {
    @Autowired
    PhanLoaiLyDoRepoInterface phanLoaiLyDoRepoInterface;

    public int countPhanLoaiLyDoByIdLydo(int idLyDo) {
        LyDo lyDo = new LyDo();
        lyDo.setIdLyDo(idLyDo);
        return phanLoaiLyDoRepoInterface.countByLyDo(lyDo);
    }

    public PhanLoaiLyDo getPhanLoaiLyDoCongDan(CongDan congDan) {
        PhanLoaiLyDo phanLoaiLyDo = new PhanLoaiLyDo();
        phanLoaiLyDo.setMoTa("");
        if (congDan.getLyDo().getPhanLoaiLyDos().size() != 0) {
            return phanLoaiLyDoRepoInterface.findOne(congDan.getIdPhanLoaiLyDo());
        }
        return phanLoaiLyDo;
    }
}

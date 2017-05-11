package com.nghiavuquansu.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.entity.Phanloailydo;
import com.nghiavuquansu.repository.PhanLoaiLyDoRepoInterface;

@Service
public class PhanLoaiLyDoService {
	@Autowired PhanLoaiLyDoRepoInterface phanLoaiLyDoRepoInterface;
	
	public int countPhanLoaiLyDoByIdLydo(int idlydo){
		Lydo lydo = new Lydo();
		lydo.setIdlydo(idlydo);
		return phanLoaiLyDoRepoInterface.countByLydo(lydo);
	}
	
	public Phanloailydo getPhanLoaiLyDoCongDan(Congdan congdan){
	    Phanloailydo phanloailydo = new Phanloailydo();
	    phanloailydo.setMota("");
	    if(congdan.getLydo().getPhanloailydos().size() != 0){
	        return phanLoaiLyDoRepoInterface.findOne(congdan.getIdphanloailydo());
	    }
	    return phanloailydo;
	}
}

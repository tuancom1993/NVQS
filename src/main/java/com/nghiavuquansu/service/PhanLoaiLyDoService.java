package com.nghiavuquansu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.repository.PhanLoaiLyDoRepoInterface;

@Service
public class PhanLoaiLyDoService {
	@Autowired PhanLoaiLyDoRepoInterface phanLoaiLyDoRepoInterface;
	
	public int countPhanLoaiLyDoByIdLydo(int idlydo){
		Lydo lydo = new Lydo();
		lydo.setIdlydo(idlydo);
		return phanLoaiLyDoRepoInterface.countByLydo(lydo);
	}
}

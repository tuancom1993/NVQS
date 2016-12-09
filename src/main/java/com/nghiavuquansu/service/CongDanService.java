package com.nghiavuquansu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.repository.CongDanRepoInterface;

@Service
public class CongDanService {
	@Autowired CongDanRepoInterface congDanRepoInterface;
	public void saveCongDan(Congdan congdan) throws Exception{
		congDanRepoInterface.save(congdan);
	}
	
	public ArrayList<Congdan> getAllCongDan(){
		return (ArrayList<Congdan>) congDanRepoInterface.findAll();
	}
}

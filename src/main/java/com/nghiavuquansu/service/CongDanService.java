package com.nghiavuquansu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.model.AgeUtil;
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
	
	public Congdan getCongDan(int idcongdan) throws Exception{
		return congDanRepoInterface.findOne(idcongdan);
	}
	
	public List<Congdan> getListCongDanQuaTuoiNghiaVu(){
		ArrayList<Congdan> congdans = (ArrayList<Congdan>) congDanRepoInterface.findAll();
		ArrayList<Congdan> congDanQuaTuoiNghiaVus = new ArrayList<>();
		for(Congdan congdan : congdans){
			int idcapdaotao = congdan.getCapdaotao().getIdcapdaotao();
			Date ngaysinh = congdan.getNgaysinh();
			if(((idcapdaotao == 2 || idcapdaotao ==3) && AgeUtil.getAge(ngaysinh) > 25) || 
					((idcapdaotao != 2 || idcapdaotao != 3) && AgeUtil.getAge(ngaysinh) > 23)){
				congDanQuaTuoiNghiaVus.add(congdan);
			}
		}
		return congDanQuaTuoiNghiaVus;
	}

	public void deleteCongDan(int idcongdan) throws Exception {
		congDanRepoInterface.delete(idcongdan);
	}

	public List<Congdan> getListCongDanTheoLyDo(int idlydo) throws Exception {
		Lydo lydo = new Lydo();
		lydo.setIdlydo(idlydo);
		List<Congdan> list = congDanRepoInterface.findByLydo(lydo); 
		return list;
	}
}

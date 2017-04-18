package com.nghiavuquansu.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.AgeUtil;
import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Lydo;
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
	
	public List<Congdan> getListCongDanQuaTuoiNghiaVu(Date dateFrom) throws ParseException{
		ArrayList<Congdan> congdans = (ArrayList<Congdan>) congDanRepoInterface.findAll();
		ArrayList<Congdan> congDanQuaTuoiNghiaVus = new ArrayList<>();
		for(Congdan congdan : congdans){
			int idcapdaotao = congdan.getCapdaotao().getIdcapdaotao();
			Date ngaysinh = congdan.getNgaysinh();
			int age = 0;
//			if ("0".equals(date) && "0".equals(month) && "0".equals(year)){
//				age = AgeUtil.getAge(ngaysinh);
//			} else {
//				String dateString = date+"/"+month+"/"+year;
//				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//				Date dateFrom = dateFormat.parse(dateString);
//				age = AgeUtil.getAge(dateFrom, ngaysinh);
//			}
			
			age = AgeUtil.getAge(dateFrom, ngaysinh);
				
//			if((idcapdaotao == 3 || idcapdaotao == 4) && age > 27){
//				congDanQuaTuoiNghiaVus.add(congdan);
//			} else if ((idcapdaotao != 4 && idcapdaotao != 3) && age > 25 ){
//				congDanQuaTuoiNghiaVus.add(congdan);
//			}
			if(isQuaTuoiNghiaVu(congdan, dateFrom))
			    congDanQuaTuoiNghiaVus.add(congdan);
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
		Iterator<Congdan> it = list.iterator();
		while(it.hasNext()){
		    Congdan congdan = it.next();
		    if (isQuaTuoiNghiaVu(congdan, AgeUtil.getDateCalculateAge()))
		        it.remove();
		}
		return list;
	}
	
	private boolean isQuaTuoiNghiaVu(Congdan congdan, Date dateFrom){
	    int age = AgeUtil.getAge(dateFrom, congdan.getNgaysinh());
	    int idcapdaotao = congdan.getCapdaotao().getIdcapdaotao();
	    
        if((idcapdaotao == 3 || idcapdaotao == 4) && age > 27){
            return true;
        } else if ((idcapdaotao != 4 && idcapdaotao != 3) && age > 25 ){
            return true;
        } else return false;
	}
}

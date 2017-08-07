package com.nghiavuquansu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.LyDo;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class LyDoService {
	@Autowired LyDoRepoInterface lyDoRepoInterface;
	
	public LyDo findLyDo(int idLyDo){
		return lyDoRepoInterface.findOne(idLyDo);
	}
	
	public boolean isExists(int idLyDo){
		return lyDoRepoInterface.exists(idLyDo);
	}
}

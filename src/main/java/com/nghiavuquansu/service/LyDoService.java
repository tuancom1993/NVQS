package com.nghiavuquansu.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.repository.LyDoRepoInterface;

@Service
public class LyDoService {
	@Autowired LyDoRepoInterface lyDoRepoInterface;
	
	public Lydo findLyDo(int idlydo){
		return lyDoRepoInterface.findOne(idlydo);
	}
	
	public boolean isExists(int idlydo){
		return lyDoRepoInterface.exists(idlydo);
	}
}

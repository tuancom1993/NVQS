package com.nghiavuquansu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.Capdaotao;
import com.nghiavuquansu.repository.CapDaoTaoRepoInterface;

@Service
public class CapDaoTaoService {
	@Autowired CapDaoTaoRepoInterface capDaoTaoRepoInterface;
	public List<Capdaotao> getListCapDaoTao(){
		ArrayList<Capdaotao> capdaotaos = (ArrayList<Capdaotao>) capDaoTaoRepoInterface.findAll();
		return capdaotaos;
	}
}

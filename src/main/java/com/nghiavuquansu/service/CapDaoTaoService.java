package com.nghiavuquansu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.CapDaoTao;
import com.nghiavuquansu.repository.CapDaoTaoRepoInterface;

@Service
public class CapDaoTaoService {
	@Autowired CapDaoTaoRepoInterface capDaoTaoRepoInterface;
	public List<CapDaoTao> getListCapDaoTao(){
		ArrayList<CapDaoTao> capDaoTaos = (ArrayList<CapDaoTao>) capDaoTaoRepoInterface.findAll();
		return capDaoTaos;
	}
}

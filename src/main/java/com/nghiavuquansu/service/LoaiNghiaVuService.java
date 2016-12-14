package com.nghiavuquansu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghiavuquansu.entity.Loainghiavu;
import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.entity.Phanloailydo;
import com.nghiavuquansu.model.JsonLoaiNghiaVu;
import com.nghiavuquansu.model.JsonLyDo;
import com.nghiavuquansu.model.JsonPhanLoaiLyDo;
import com.nghiavuquansu.repository.LoaiNghiaVuRepoInterface;
import com.nghiavuquansu.repository.LyDoRepoInterface;
import com.nghiavuquansu.repository.PhanLoaiLyDoRepoInterface;

@Service
public class LoaiNghiaVuService {
	@Autowired
	LoaiNghiaVuRepoInterface loaiNghiaVuRepoInterface;
	@Autowired
	LyDoRepoInterface lyDoRepoInterface;
	@Autowired
	PhanLoaiLyDoRepoInterface phanLoaiLyDoRepoInterface;

	public List<Loainghiavu> getListLoaiNghiaVu() {
		ArrayList<Loainghiavu> listLoaiNgiaVu = (ArrayList<Loainghiavu>) loaiNghiaVuRepoInterface.findAll();
		return listLoaiNgiaVu;
	}

	private ArrayList<Lydo> getListLyDoByLoaiNghiaVu(Loainghiavu loainghiavu) {
		ArrayList<Lydo> lydos = lyDoRepoInterface.findLydoByLoainghiavu(loainghiavu);
		for (Lydo lydo : lydos) {
			ArrayList<Phanloailydo> phanloailydos = getListPhanLoaiLyDoByLyDo(lydo);
			lydo.setPhanloailydos(phanloailydos);
		}
		return lydos;
	}

	private ArrayList<Phanloailydo> getListPhanLoaiLyDoByLyDo(Lydo lydo) {
		return phanLoaiLyDoRepoInterface.findPhanloailydoByLydo(lydo);
	}

	public ArrayList<JsonLoaiNghiaVu> getListJsonLoaiNghiaVu() {
		ArrayList<Loainghiavu> listLoaiNgiaVu = (ArrayList<Loainghiavu>) getListLoaiNghiaVu();
		ArrayList<JsonLoaiNghiaVu> listJsonLoaiNghiaVu = new ArrayList<>();
		for (Loainghiavu loainghiavu : listLoaiNgiaVu) {
			JsonLoaiNghiaVu jsonLoaiNghiaVu = new JsonLoaiNghiaVu(loainghiavu.getIdloainghiavu(), loainghiavu.getMota(),
					getListJsonLyDo(loainghiavu.getLydos()));
			listJsonLoaiNghiaVu.add(jsonLoaiNghiaVu);
		}
		return listJsonLoaiNghiaVu;
	}

	private ArrayList<JsonLyDo> getListJsonLyDo(List<Lydo> listLyDo) {
		ArrayList<JsonLyDo> listJsonLyDo = new ArrayList<>();
		for (Lydo lydo : listLyDo) {
			JsonLyDo jsonLyDo = new JsonLyDo(lydo.getIdlydo(), lydo.getMota(), lydo.getDanhsach(),
					getListJsonPhanLoaiLyDo(lydo.getPhanloailydos()));
			listJsonLyDo.add(jsonLyDo);
		}
		return listJsonLyDo;
	}

	private ArrayList<JsonPhanLoaiLyDo> getListJsonPhanLoaiLyDo(List<Phanloailydo> listPhanLoaiLyDo) {
		if (listPhanLoaiLyDo == null || listPhanLoaiLyDo.isEmpty()) {
			return new ArrayList<>();
		} else {
			ArrayList<JsonPhanLoaiLyDo> listJsonPhanLoaiLyDo = new ArrayList<>();
			for (Phanloailydo phanloailydo : listPhanLoaiLyDo) {
				JsonPhanLoaiLyDo jsonPhanLoaiLyDo = new JsonPhanLoaiLyDo(phanloailydo.getIdphanloailydo(),
						phanloailydo.getMota());
				listJsonPhanLoaiLyDo.add(jsonPhanLoaiLyDo);
			}
			return listJsonPhanLoaiLyDo;
		}

	}

}

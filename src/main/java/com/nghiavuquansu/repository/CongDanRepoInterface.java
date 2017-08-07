package com.nghiavuquansu.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.nghiavuquansu.entity.CongDan;
import com.nghiavuquansu.entity.LyDo;

public interface CongDanRepoInterface extends CrudRepository<CongDan, Integer> {
	public ArrayList<CongDan> findByLyDo(LyDo lydo);
	public ArrayList<CongDan> findByIdPhanLoaiLyDo(int idPhanLoaiLyDo);
}

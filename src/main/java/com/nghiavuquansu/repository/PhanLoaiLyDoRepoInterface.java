package com.nghiavuquansu.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.nghiavuquansu.entity.LyDo;
import com.nghiavuquansu.entity.PhanLoaiLyDo;

public interface PhanLoaiLyDoRepoInterface extends CrudRepository<PhanLoaiLyDo, Integer> {
	ArrayList<PhanLoaiLyDo> findPhanLoaiLyDoByLyDo(LyDo lyDo);
	int countByLyDo(LyDo lyDo);
}

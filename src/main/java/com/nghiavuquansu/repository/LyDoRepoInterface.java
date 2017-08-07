package com.nghiavuquansu.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.nghiavuquansu.entity.LoaiNghiaVu;
import com.nghiavuquansu.entity.LyDo;

public interface LyDoRepoInterface extends CrudRepository<LyDo, Integer> {
	ArrayList<LyDo> findLyDoByLoaiNghiaVu(LoaiNghiaVu loaiNghiaVu);
}

package com.nghiavuquansu.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.nghiavuquansu.entity.Lydo;
import com.nghiavuquansu.entity.Phanloailydo;

public interface PhanLoaiLyDoRepoInterface extends CrudRepository<Phanloailydo, Integer> {
	ArrayList<Phanloailydo> findPhanloailydoByLydo(Lydo lydo);
	int countByLydo(Lydo lydo);
}

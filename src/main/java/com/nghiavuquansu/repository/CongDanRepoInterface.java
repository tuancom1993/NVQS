package com.nghiavuquansu.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nghiavuquansu.entity.Congdan;
import com.nghiavuquansu.entity.Lydo;

public interface CongDanRepoInterface extends CrudRepository<Congdan, Integer> {
	public ArrayList<Congdan> findByLydo(Lydo lydo);
	public ArrayList<Congdan> findByIdphanloailydo(int idphanloailydo);
}

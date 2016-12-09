package com.nghiavuquansu.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.nghiavuquansu.entity.Loainghiavu;
import com.nghiavuquansu.entity.Lydo;

public interface LyDoRepoInterface extends CrudRepository<Lydo, Integer> {
	ArrayList<Lydo> findLydoByLoainghiavu(Loainghiavu loainghiavu);
}

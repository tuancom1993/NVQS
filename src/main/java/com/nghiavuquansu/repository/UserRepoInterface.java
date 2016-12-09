package com.nghiavuquansu.repository;

import org.springframework.data.repository.CrudRepository;

import com.nghiavuquansu.entity.User;

public interface UserRepoInterface extends CrudRepository<User, String>{

}

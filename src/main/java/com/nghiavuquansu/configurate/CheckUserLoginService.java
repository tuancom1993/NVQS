package com.nghiavuquansu.configurate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.repository.UserRepoInterface;

@Service
public class CheckUserLoginService implements UserDetailsService {
	@Autowired UserRepoInterface userRepoInterface;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepoInterface.findOne(username);
		if (user == null) throw new UsernameNotFoundException("Khong tim thay user voi username: "+username);
		return new CustomUserDetail(user);
	}

}

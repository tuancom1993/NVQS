package com.nghiavuquansu.common;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {
	public static PasswordEncoder getPasswordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	public static String encode(String str){
		return getPasswordEncoder().encode(str);
	}
	
	public static boolean equals(String str, String code){
		return getPasswordEncoder().matches(str, code);
	}
}

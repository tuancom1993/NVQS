package com.nghiavuquansu.common;

import org.springframework.security.core.context.SecurityContextHolder;

import com.nghiavuquansu.configurate.CustomUserDetail;
import com.nghiavuquansu.entity.User;

public class Utils {
    public static User getUserLoging() {
        User userLogin = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetail) {
            CustomUserDetail customUserDetails = (CustomUserDetail) principal;
            userLogin = customUserDetails.getUser();
        }
        return userLogin;
    }

    public static String getExtension(String originalFilename) {
        String[] arrString = originalFilename.split("\\.");
        try {
            return arrString[arrString.length - 1];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

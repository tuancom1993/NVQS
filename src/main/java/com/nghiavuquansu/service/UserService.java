package com.nghiavuquansu.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nghiavuquansu.common.MessageUtils;
import com.nghiavuquansu.common.PasswordEncoderUtils;
import com.nghiavuquansu.configurate.CustomUserDetail;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.exception.UserException;
import com.nghiavuquansu.model.MatKhauModel;
import com.nghiavuquansu.repository.UserRepoInterface;

@Service
public class UserService {
    @Autowired
    UserRepoInterface userRepoInterface;

    public User getUserByUsername(String username) {
        return userRepoInterface.findOne(username);
    }

    public User editPersonalUserInformation(User userUpdate) {
        User userLogin = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof CustomUserDetail) {
            CustomUserDetail customUserDetails = (CustomUserDetail) principal;
            userLogin = customUserDetails.getUser();
        }
        userUpdate.setUsername(userLogin.getUsername());
        // if(userLogin.getQuyen() != 1) {
        // userUpdate.setQuyen(userLogin.getQuyen());
        // }
        userUpdate.setQuyen(userLogin.getQuyen());
        userUpdate.setPassword(userLogin.getPassword());
        userUpdate.setIsBlocked(userLogin.getIsBlocked());
        userRepoInterface.save(userUpdate);
        
        //Set up new Pricipal for SpringSecurity
        Object newPricipal = new CustomUserDetail(userUpdate);
        /*List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        String newRole = userUpdated.getQuyen() == 1 ? "ROLE_ADMIN" : "ROLE_USER";
        updatedAuthorities.add(new SimpleGrantedAuthority(newRole));*/
        
        Authentication newAuth = new UsernamePasswordAuthenticationToken(newPricipal, auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        
        return userUpdate;
    }

    public MatKhauModel doiMatKhau(MatKhauModel matKhauModel, User userLogin) {
        matKhauModel.setMess(null);

        if (!PasswordEncoderUtils.equals(matKhauModel.getOldPassword(), userLogin.getPassword())) {
            matKhauModel.setMess(MessageUtils.PASSWORD_NOT_CORRECT);
            return matKhauModel;
        }
        if (!matKhauModel.getNewPassword().equals(matKhauModel.getReNewPassword())) {
            matKhauModel.setMess(MessageUtils.NEW_PASSWORD_AND_RENEW_PASSWORD_NOT_CORRECT);
            return matKhauModel;
        }
        if (matKhauModel.getNewPassword() == null || matKhauModel.getNewPassword().length() == 0) {
            matKhauModel.setMess(MessageUtils.PASSWORD_CAN_NOT_EMPTY);
            return matKhauModel;
        }
        userLogin.setPassword(PasswordEncoderUtils.encode(matKhauModel.getNewPassword()));
        userRepoInterface.save(userLogin);
        matKhauModel.setMess(MessageUtils.CHANGE_PASSWORD_SUCCESSFUL);
        return matKhauModel;
    }

    public List<User> getUsers() {
        return (List<User>) userRepoInterface.findAll();
    }

    public User addNewUser(User user) {
        try {
            if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())
                    || StringUtils.isEmpty(user.getHoTen()))
                throw new UserException(MessageUtils.FIELDS_CANNOT_EMPTY);

            if (user.getUsername().length() >= 25)
                throw new UserException(MessageUtils.USERNAME_LENGHT_TOO_LAGER);

            if (haveSpecialCharacter(user.getUsername()))
                throw new UserException(MessageUtils.USERNAME_HAVE_SPECIAL_CHARACTER);

            if (userRepoInterface.exists(user.getUsername()))
                throw new UserException(MessageUtils.EXIST_USERNAME + user.getUsername());

            if (!user.getPassword().equals(user.getRePassword()))
                throw new UserException(MessageUtils.PASSWORD_AND_REPASSWORD_NOT_CORRECT);

            user.setPassword(PasswordEncoderUtils.encode(user.getPassword()));
            userRepoInterface.save(user);
            user.setErrorMessage(MessageUtils.ADD_NEW_USER_SUCCESSFUL);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            user.setErrorMessage(e.getMessage());
            return user;
        }
    }

    public User editUser(User user) {
        try {
            System.out.println("USERNAME EDIT: " + user.getUsername() + user.getHoTen());
            User userUpdate = userRepoInterface.findOne(user.getUsername());
            if (user.getQuyen() == 1)
                throw new Exception("Cannot edit ADMIN");

            userUpdate.setHoTen(user.getHoTen());
            userUpdate.setGhiChu(user.getGhiChu());
            userUpdate.setIsBlocked(user.getIsBlocked());
            userRepoInterface.save(userUpdate);
            user.setErrorMessage(MessageUtils.EDIT_USER_SUCCESSFUL);

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            user.setErrorMessage(MessageUtils.EDIT_USER_FAILL);
            return user;
        }
    }

    private boolean haveSpecialCharacter(String str) {
        Pattern p = Pattern.compile("[^a-z0-9]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public boolean restorePassword(String username) {
        try {
            User user = userRepoInterface.findOne(username);
            if (user == null)
                throw new UserException(MessageUtils.CANOT_LOAD_USER_WITH_USERNAME + username);
            user.setPassword(PasswordEncoderUtils.encode(PasswordEncoderUtils.PASSWORD_DEFAULT));
            userRepoInterface.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

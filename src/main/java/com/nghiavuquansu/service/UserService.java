package com.nghiavuquansu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nghiavuquansu.common.MessageUtils;
import com.nghiavuquansu.common.PasswordEncoderUtils;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.exception.UserException;
import com.nghiavuquansu.model.MatKhauModel;
import com.nghiavuquansu.repository.UserRepoInterface;

@Service
public class UserService {
    @Autowired 
    UserRepoInterface userRepoInterface;
    
    public User getUserByUsername(String username){
        return userRepoInterface.findOne(username);
    }
    
    public User editUser(User userUpdate, User userLogin){
        userUpdate.setUsername(userLogin.getUsername());
        if(userLogin.getQuyen() != 1) {
            userUpdate.setQuyen(userLogin.getQuyen());
        }
        userUpdate.setPassword(userLogin.getPassword());
        userRepoInterface.save(userUpdate);
        return userUpdate;
    }
    
    public MatKhauModel doiMatKhau(MatKhauModel matKhauModel, User userLogin){
        matKhauModel.setMess(null);
        
        if (!PasswordEncoderUtils.equals(matKhauModel.getOldPassword(), userLogin.getPassword())){
            matKhauModel.setMess(MessageUtils.PASSWORD_NOT_CORRECT);
            return matKhauModel;
        }
        if(!matKhauModel.getNewPassword().equals(matKhauModel.getReNewPassword())){
            matKhauModel.setMess(MessageUtils.NEW_PASSWORD_AND_RENEW_PASSWORD_NOT_CORRECT);
            return matKhauModel;
        }
        if(matKhauModel.getNewPassword() == null || matKhauModel.getNewPassword().length() == 0){
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
    
    public User addNewUser(User user){
        try {
            if(StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()) 
                    || StringUtils.isEmpty(user.getHoten()))
                throw new UserException(MessageUtils.FIELDS_CANNOT_EMPTY);
            
            if(userRepoInterface.exists(user.getUsername()))
                throw new UserException(MessageUtils.EXIST_USERNAME + user.getUsername());
            
            if(!user.getPassword().equals(user.getRePassword()))
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
}

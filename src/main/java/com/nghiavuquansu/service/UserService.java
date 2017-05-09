package com.nghiavuquansu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.MessageUtils;
import com.nghiavuquansu.common.PasswordEncoderUtils;
import com.nghiavuquansu.entity.User;
import com.nghiavuquansu.model.MatKhauModel;
import com.nghiavuquansu.repository.UserRepoInterface;

@Service
public class UserService {
    @Autowired 
    UserRepoInterface userRepoInterface;
    
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

    public Object getUsers() {
        // TODO Auto-generated method stub
        return userRepoInterface.findAll();
    }
}

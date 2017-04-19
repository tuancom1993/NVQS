package com.nghiavuquansu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiavuquansu.common.PasswordEncoderUtil;
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
        
        if (!PasswordEncoderUtil.equals(matKhauModel.getOldPassword(), userLogin.getPassword())){
            matKhauModel.setMess("Mật khẩu không chính xác");
            return matKhauModel;
        }
        if(!matKhauModel.getNewPassword().equals(matKhauModel.getReNewPassword())){
            matKhauModel.setMess("Mật khẩu mới và Mật khẩu nhập lại không khớp");
            return matKhauModel;
        }
        if(matKhauModel.getNewPassword() == null || matKhauModel.getNewPassword().length() == 0){
            matKhauModel.setMess("Không được bỏ trống mật khẩu mới");
            return matKhauModel;
        }
        userLogin.setPassword(PasswordEncoderUtil.encode(matKhauModel.getNewPassword()));
        userRepoInterface.save(userLogin);
        matKhauModel.setMess("Đổi mật khẩu thành công");
        return matKhauModel;
    }
}

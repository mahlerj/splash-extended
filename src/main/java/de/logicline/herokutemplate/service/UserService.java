package de.logicline.herokutemplate.service;


import java.util.List;
import java.util.Map;

import de.logicline.herokutemplate.model.UserEntity;
import de.logicline.herokutemplate.model.UserInfoEntity;

public interface UserService {
    
    public void addUser(UserEntity userEntitiy);
    
    public void addUser(List<UserEntity> userEntitiy);
    
    public void addUserInfo(List<UserInfoEntity> userInfoEntitiy);
    
    public UserEntity getUserByNameAndPassword(String username, String password);
    
    public Integer createUser(UserInfoEntity userInfoEntity);
    
    public List<UserInfoEntity> getUserInfoList();
    
    public UserInfoEntity getUserInfo(String token);
    
    public UserInfoEntity getUserInfoByUserId(Integer userId);
    
    public void updateUserInfo(String token, UserInfoEntity userInfoEntity);
    
    public void updateUserInfoByUserId(Integer userId, UserInfoEntity userInfoEntity);
    
    public void updatePassword(Integer userId);
    
    public Map<Integer, String> getAllCustomer();
    
    public Map<Integer, String> searchUserByCustomerId(String customerId);
  
}

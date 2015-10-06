package de.logicline.herokutemplate.service;

import java.util.List;
import java.util.Map;

import de.logicline.herokutemplate.dto.UserInfoEntity;
import de.logicline.herokutemplate.model.ContactEntity;
import de.logicline.herokutemplate.model.UserEntity;

public interface UserService {

	public UserEntity getUserByNameAndPassword(String username, String password);

	public Integer createUser(UserInfoEntity userInfoEntity);

	public List<ContactEntity> getContactList();

	public ContactEntity getContact(String token);

	public ContactEntity getContactByUserId(Integer userId);

	public void updateUserInfo(String token, UserInfoEntity userInfoEntity);

	public void updateUserInfoByUserId(Integer userId,
			UserInfoEntity userInfoEntity);

	public void updatePassword(Integer userId);

	public Map<Integer, String> getCustomerIdMap();

	public Map<Integer, String> searchUserByCustomerId(String customerId);

}

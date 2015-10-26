package de.logicline.splash.service;

import java.util.List;
import java.util.Map;

import de.logicline.splash.dto.ContactDto;
import de.logicline.splash.model.ContactEntity;
import de.logicline.splash.model.UserEntity;

public interface UserService {

	public UserEntity getUserByNameAndPassword(String username, String password);

	// public String createUser(ContactDto contactDto);

	public List<ContactEntity> getContactList();

	public ContactEntity getContact(String token);

	public ContactEntity getContactByUserId(String userId);

	public void updateUserInfoByUserId(String userId, ContactDto contactDto);

	public String updatePassword(String userId);

	public Map<String, String> getCustomerIdMap();

	public Map<String, String> searchUserByName(String name);

}

package de.logicline.splash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.logicline.splash.dao.ContactDao;
import de.logicline.splash.dao.UserDao;
import de.logicline.splash.dto.ContactDto;
import de.logicline.splash.model.ContactEntity;
import de.logicline.splash.model.UserEntity;
import de.logicline.splash.utils.Enums;
import de.logicline.splash.utils.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	EntityManager em;

	@Autowired
	UserDao userDao;

	@Autowired
	ContactDao contactDao;

	// @Transactional
	// public String createUser(ContactDto contactDto) {
	//
	// UserEntity ue = new UserEntity();
	// ue.setUsername(contactDto.getMainSurname());
	// String password = new PasswordGenerator().generatePswd(10, 10, 2, 2, 2);
	// ue.setPassword(password);
	// String token = DigestUtils.md5Hex(password
	// + contactDto.getMainSurname());
	// ue.setToken(token);
	// ue.setRole(Enums.UserRole.ROLE_CUSTOMER.name());
	// userDao.create(ue);
	// ContactEntity ce = contactDto.toEntity(new ContactEntity());
	// ce.setUserIdFk(ue.getUserId());
	// contactDao.create(ce);
	// return password;
	// };

	public UserEntity getUserByNameAndPassword(String username, String password) {
		return userDao.getUserByNameAndPassword(username, password);

	}

	public List<ContactEntity> getContactList() {
		List<ContactEntity> resultList = contactDao.findAll();
		return resultList;
	}

	public Map<String, String> getCustomerIdMap() {

		List<ContactEntity> resultList = contactDao.findAll();

		Map<String, String> customerIdMap = new HashMap<String, String>();

		for (ContactEntity uie : resultList) {
			customerIdMap.put(uie.getUserIdFk(), uie.getLastName());
		}

		return customerIdMap;

	}

	public ContactEntity getContact(String token) {

		String userId = userDao.getUserId(token);
		ContactEntity result = contactDao.getContactByUserId(userId);

		return result;
	}

	public ContactEntity getContactByUserId(String userId) {
		ContactEntity result = contactDao.getContactByUserId(userId);

		return result;
	};

	@Transactional
	public void updateUserInfoByUserId(String userId, ContactDto contactDto) {
		ContactEntity contactOld = contactDao.getContactByUserId(userId);

		ContactEntity contactUpdated = contactDto.toEntity(contactOld);
		contactDao.edit(contactUpdated);

	}

	public Map<String, String> searchUserByName(String name) {

		List<ContactEntity> resultList = contactDao.findByName(name);

		Map<String, String> customerIdMap = new HashMap<String, String>();

		for (ContactEntity contact : resultList) {
			customerIdMap.put(contact.getUserIdFk(), contact.getLastName());
		}

		return customerIdMap;

	}

	@Transactional
	public String updatePassword(String userId) {
		String password = new PasswordGenerator().generatePswd(10, 10, 2, 2, 2);
		UserEntity userForUpdate = userDao.find(userId);
		userForUpdate.setPassword(password);
		userDao.edit(userForUpdate);
		return password;

	}

	@Transactional
	public UserEntity createWebAccount(String userId) {

		ContactEntity ce = contactDao.getContactByUserId(userId);

		String password = new PasswordGenerator().generatePswd(10, 10, 2, 2, 2);
		UserEntity ue = userDao.find(userId);
		if (ue == null) {
			ue = new UserEntity();
			ue.setUserId(ce.getUserIdFk());
			ue.setUsername(ce.getLastName());
			ue.setPassword(password);
			String token = DigestUtils.md5Hex(password + ce.getLastName());
			ue.setToken(token);
			ue.setRole(Enums.UserRole.ROLE_CUSTOMER.name());
			userDao.create(ue);

		}
		ue.setPassword(password);
		userDao.edit(ue);

		return ue;

	}
}

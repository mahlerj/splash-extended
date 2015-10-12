package de.logicline.herokutemplate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.logicline.herokutemplate.dao.ContactDao;
import de.logicline.herokutemplate.dao.UserDao;
import de.logicline.herokutemplate.dto.ContactDto;
import de.logicline.herokutemplate.model.ContactEntity;
import de.logicline.herokutemplate.model.UserEntity;
import de.logicline.herokutemplate.utils.Enums;
import de.logicline.herokutemplate.utils.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService {

	private Logger log = Logger.getLogger(this.getClass().getName());

	@PersistenceContext
	EntityManager em;

	@Autowired
	UserDao userDao;

	@Autowired
	ContactDao contactDao;

	@Transactional
	public Integer createUser(ContactDto contactDto) {

		UserEntity ue = new UserEntity();
		ue.setUsername(contactDto.getCustomerId());
		String password = new PasswordGenerator().generatePswd(10, 10, 2, 2, 2);
		ue.setPassword(password);
		String token = DigestUtils
				.md5Hex(password + contactDto.getCustomerId());
		ue.setToken(token);
		ue.setRole(Enums.UserRole.ROLE_CUSTOMER.name());
		// ue.setUserId(44);
		userDao.create(ue);
		contactDto.setUserIdFk(ue.getUserId());
		contactDao.create(contactDto.toEntity(new ContactEntity()));

		return ue.getUserId();
	};

	public UserEntity getUserByNameAndPassword(String username, String password) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cq.from(UserEntity.class);
		cq.select(user);
		cq.where(cb.and(cb.equal(user.get("username"), username),
				cb.equal(user.get("password"), password)));
		List<UserEntity> resultList = em.createQuery(cq).getResultList();

		UserEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}

		return result;
	}

	private Integer getUserId(String token) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
		Root<UserEntity> user = cq.from(UserEntity.class);
		cq.select(user);
		cq.where(cb.equal(user.get("token"), token));
		List<UserEntity> resultList = em.createQuery(cq).getResultList();

		UserEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
			return result.getUserId();
		} else {
			return null;
		}

	}

	public List<ContactEntity> getContactList() {
		List<ContactEntity> resultList = contactDao.findAll();
		return resultList;
	}

	public Map<Integer, String> getCustomerIdMap() {

		List<ContactEntity> resultList = contactDao.findAll();

		Map<Integer, String> customerIdMap = new HashMap<Integer, String>();

		for (ContactEntity uie : resultList) {
			customerIdMap.put(uie.getUserIdFk(), uie.getCustomerId());
		}

		return customerIdMap;

	}

	public ContactEntity getContact(String token) {

		Integer userId = getUserId(token);

		ContactEntity result = getContactByUserId(userId);

		return result;
	}

	public ContactEntity getContactByUserId(Integer userId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContactEntity> cq = cb.createQuery(ContactEntity.class);
		Root<ContactEntity> userInfo = cq.from(ContactEntity.class);
		cq.select(userInfo);
		cq.where(cb.equal(userInfo.get("userIdFk"), userId));
		List<ContactEntity> resultList = em.createQuery(cq).getResultList();

		ContactEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}

		return result;
	}

	@Transactional
	public void updateUserInfo(String token, ContactDto contactDto) {
		ContactEntity contactOld = getContact(token);
		if (contactOld.getUserIdFk().equals(contactDto.getUserIdFk())) {
			ContactEntity contactUpdated = contactDto.toEntity(contactOld);
			contactDao.edit(contactUpdated);
		}
	}

	@Transactional
	public void updateUserInfoByUserId(Integer userId, ContactDto contactDto) {
		ContactEntity contactOld = getContactByUserId(userId);
		if (contactOld.getUserIdFk().equals(contactDto.getUserIdFk())) {
			ContactEntity contactUpdated = contactDto.toEntity(contactOld);
			contactDao.edit(contactUpdated);
		}
	}

	public Map<Integer, String> searchUserByCustomerId(String customerId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ContactEntity> cq = cb.createQuery(ContactEntity.class);
		Root<ContactEntity> userInfo = cq.from(ContactEntity.class);
		cq.select(userInfo);
		cq.where(cb.like(userInfo.<String> get("customerId"), "%" + customerId
				+ "%"));
		List<ContactEntity> resultList = em.createQuery(cq).getResultList();

		Map<Integer, String> customerIdMap = new HashMap<Integer, String>();

		for (ContactEntity contact : resultList) {
			customerIdMap.put(contact.getUserIdFk(), contact.getCustomerId());
		}

		return customerIdMap;

	}

	@Transactional
	public String updatePassword(Integer userId) {
		String password = new PasswordGenerator().generatePswd(10, 10, 2, 2, 2);
		UserEntity userForUpdate = em.find(UserEntity.class, userId);
		userForUpdate.setPassword(password);
		em.persist(userForUpdate);
		return password;

	}

	/**
	 * @Transactional public List<Person> listPeople() { CriteriaQuery<Person> c
	 *                = em.getCriteriaBuilder().createQuery(Person.class);
	 *                c.from(Person.class); return
	 *                em.createQuery(c).getResultList(); }
	 * @Transactional public void removePerson(Integer id) { Person person =
	 *                em.find(Person.class, id); if (null != person) {
	 *                em.remove(person); } }
	 **/

}

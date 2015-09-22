package de.logicline.parkingportal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.logicline.parkingportal.model.ContractInfoEntity;
import de.logicline.parkingportal.model.UserEntity;
import de.logicline.parkingportal.model.UserInfoEntity;
import de.logicline.parkingportal.utils.Enums;
import de.logicline.parkingportal.utils.PasswordGenerator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Service
public class UserServiceImpl implements UserService {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public void addUser(UserEntity userEntity) {
		
		em.persist(userEntity);		
		 try {
		        em.flush();  
		    } catch (PersistenceException e) {  		        
		    	log.error("Creating UserEntity failed", e);  
	
		    }
	}
	
	@Transactional
	public void addUser(List<UserEntity> userEntityList) {

		if (userEntityList.size() > 1000) {
			return;
		}
		
		for (UserEntity ue : userEntityList) {
			addUser(ue);
		}
	}

	@Transactional
	public void addUserInfo(List<UserInfoEntity> userInfoEntityList) {

		if (userInfoEntityList.size() > 1000) {
			return;
		}
		for (UserInfoEntity uie : userInfoEntityList) {
			addUserInfo(uie);
		}
	}
	
	@Transactional
	public void addUserInfo(UserInfoEntity uie){
		em.persist(uie);
		 try {
		        em.flush();  
		    } catch (PersistenceException e) {  		        
		    	log.error("Creating UserEntity failed", e);  
		    }
	}
	
	@Transactional
	public Integer createUser(UserInfoEntity userInfoEntity){
		
		UserEntity ue = new UserEntity();
		ue.setUsername(userInfoEntity.getOrgNr());
		String password = new PasswordGenerator().generatePswd(10,10,2,2,2);
		ue.setPassword(password);
		String token = DigestUtils.md5Hex(userInfoEntity.getOrgNr() + userInfoEntity.getCustomerId());
		ue.setToken(token);
		ue.setRole(Enums.UserRole.ROLE_CUSTOMER.name());
		addUser(ue);
		userInfoEntity.setUserIdFk(ue.getUserId());
		addUserInfo(userInfoEntity);
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
	

	public List<UserInfoEntity> getUserInfoList() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserInfoEntity> cq = cb.createQuery(UserInfoEntity.class);
		Root<UserInfoEntity> userInfo = cq.from(UserInfoEntity.class);
		cq.select(userInfo);
		List<UserInfoEntity> resultList = em.createQuery(cq).getResultList();
		return resultList;
	}
	
	public UserInfoEntity getUserInfo(String token) {

		Integer userId = getUserId(token);

		UserInfoEntity result = getUserInfoByUserId(userId);

		return result;
	}

	public UserInfoEntity getUserInfoByUserId(Integer userId) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserInfoEntity> cq = cb.createQuery(UserInfoEntity.class);
		Root<UserInfoEntity> userInfo = cq.from(UserInfoEntity.class);
		cq.select(userInfo);
		cq.where(cb.equal(userInfo.get("userIdFk"), userId));
		List<UserInfoEntity> resultList = em.createQuery(cq).getResultList();

		UserInfoEntity result = null;
		if (resultList != null && resultList.size() > 0) {
			result = resultList.get(0);
		}

		return result;
	}

	@Transactional
	public void updateUserInfo(String token, UserInfoEntity userInfoEntity) {
		UserInfoEntity userInfoEntityOld = getUserInfo(token);
		if (userInfoEntityOld.getUserIdFk().equals(userInfoEntity.getUserIdFk())) {
			em.merge(userInfoEntity);
			em.flush();
		}
	}
	
	@Transactional
	public void updateUserInfoByUserId(Integer userId, UserInfoEntity userInfoEntity) {
		UserInfoEntity userInfoEntityOld = getUserInfoByUserId(userId);
		if (userInfoEntityOld.getUserIdFk().equals(userInfoEntity.getUserIdFk())) {
			em.merge(userInfoEntity);
			em.flush();
		}
	}
	
	public Map<Integer, String> getAllCustomer(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserInfoEntity> cq = cb.createQuery(UserInfoEntity.class);
		Root<UserInfoEntity> userInfo = cq.from(UserInfoEntity.class);
		cq.select(userInfo);
		List<UserInfoEntity> resultList = em.createQuery(cq).getResultList();	
		
		Map<Integer, String> customerIdMap = new HashMap<Integer, String>();
		
		for(UserInfoEntity uie : resultList){
			customerIdMap.put(uie.getUserIdFk(), uie.getCustomerId());
		}
		
		return customerIdMap;
		
	}
	
	public Map<Integer, String> searchUserByCustomerId(String customerId){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserInfoEntity> cq = cb.createQuery(UserInfoEntity.class);
		Root<UserInfoEntity> userInfo = cq.from(UserInfoEntity.class);
		cq.select(userInfo);
		cq.where(cb.like(userInfo.<String>get("customerId"), "%"+ customerId + "%"));
		List<UserInfoEntity> resultList = em.createQuery(cq).getResultList();	
		
		Map<Integer, String> customerIdMap = new HashMap<Integer, String>();
		
		for(UserInfoEntity uie : resultList){
			customerIdMap.put(uie.getUserIdFk(), uie.getCustomerId());
		}
		
		return customerIdMap;
		
	}
	
	@Transactional
	public void updatePassword(Integer userId){
		String password = new PasswordGenerator().generatePswd(10,10,2,2,2);
		UserEntity userForUpdate = em.find(UserEntity.class, userId);
		userForUpdate.setPassword(password);
		em.persist(userForUpdate);	
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

package de.logicline.herokutemplate.dao;

import de.logicline.herokutemplate.model.UserEntity;

public interface UserDao extends AbstractDao<UserEntity> {

	public Integer getUserId(String token);

	public UserEntity getUser(String token);

	public UserEntity getUserByNameAndPassword(String username, String password);

}
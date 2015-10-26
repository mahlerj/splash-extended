package de.logicline.splash.dao;

import de.logicline.splash.model.UserEntity;

public interface UserDao extends AbstractDao<UserEntity> {

	public String getUserId(String token);

	public UserEntity getUser(String token);

	public UserEntity getUserByNameAndPassword(String username, String password);

}
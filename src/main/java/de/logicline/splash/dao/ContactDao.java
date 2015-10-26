package de.logicline.splash.dao;

import java.util.List;

import de.logicline.splash.model.ContactEntity;

public interface ContactDao extends AbstractDao<ContactEntity> {

	public ContactEntity getContactByUserId(String userId);

	public List<ContactEntity> findByName(String name);

}
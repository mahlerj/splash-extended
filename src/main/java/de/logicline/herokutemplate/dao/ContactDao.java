package de.logicline.herokutemplate.dao;

import java.util.List;

import de.logicline.herokutemplate.model.ContactEntity;

public interface ContactDao extends AbstractDao<ContactEntity> {

	public ContactEntity getContactByUserId(Integer userId);

	public List<ContactEntity> findByCustomerId(String id);

}
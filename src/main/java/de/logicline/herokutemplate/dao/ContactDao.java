package de.logicline.herokutemplate.dao;

import java.util.List;

import de.logicline.herokutemplate.model.ContactEntity;

public interface ContactDao extends AbstractDao<ContactEntity> {

	public List<ContactEntity> findByCustomerId(String id);

	public List<ContactEntity> findByCustomerId(String id, String countrySfid);

}
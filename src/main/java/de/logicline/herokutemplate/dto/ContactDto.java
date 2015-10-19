package de.logicline.herokutemplate.dto;

import de.logicline.herokutemplate.model.ContactEntity;

public class ContactDto {

	private Integer userIdFk;
	private String email;
	private String mainName;
	private String mainSurname;
	private String mainCity;
	private String mainCountry;
	private String mainZipcode;
	private String mainStreet;
	private String mainPhone;

	public Integer getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Integer userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public String getMainSurname() {
		return mainSurname;
	}

	public void setMainSurname(String mainSurname) {
		this.mainSurname = mainSurname;
	}

	public String getMainCity() {
		return mainCity;
	}

	public void setMainCity(String mainCity) {
		this.mainCity = mainCity;
	}

	public String getMainCountry() {
		return mainCountry;
	}

	public void setMainCountry(String mainCountry) {
		this.mainCountry = mainCountry;
	}

	public String getMainZipcode() {
		return mainZipcode;
	}

	public void setMainZipcode(String mainZipcode) {
		this.mainZipcode = mainZipcode;
	}

	public String getMainStreet() {
		return mainStreet;
	}

	public void setMainStreet(String mainStreet) {
		this.mainStreet = mainStreet;
	}

	public String getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	public ContactEntity toEntity(ContactEntity contactEntity) {

		contactEntity.setEmail(getEmail());
		contactEntity.setFirstName(getMainName());
		contactEntity.setLastName(getMainSurname());
		contactEntity.setMailingCity(getMainCity());
		contactEntity.setMailingCountry(getMainCountry());
		contactEntity.setMailingPostalCode(getMainZipcode());
		contactEntity.setMailingStreet(getMainStreet());
		contactEntity.setPhone(getMainPhone());

		return contactEntity;
	}

}

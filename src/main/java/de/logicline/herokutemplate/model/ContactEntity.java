package de.logicline.herokutemplate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.logicline.herokutemplate.dto.ContactDto;

@Entity
public class ContactEntity {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer userIdFk;

	private String customerId;
	private String email;
	private String firstName;
	private String lastName;
	private String mailingCity;
	private String mailingCountry;
	private String mailingPostalCode;
	private String mailingStreet;
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserIdFk() {
		return userIdFk;
	}

	public void setUserIdFk(Integer userIdFk) {
		this.userIdFk = userIdFk;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMailingCity() {
		return mailingCity;
	}

	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}

	public String getMailingCountry() {
		return mailingCountry;
	}

	public void setMailingCountry(String mailingCountry) {
		this.mailingCountry = mailingCountry;
	}

	public String getMailingPostalCode() {
		return mailingPostalCode;
	}

	public void setMailingPostalCode(String mailingPostalCode) {
		this.mailingPostalCode = mailingPostalCode;
	}

	public String getMailingStreet() {
		return mailingStreet;
	}

	public void setMailingStreet(String mailingStreet) {
		this.mailingStreet = mailingStreet;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ContactDto toDto() {
		ContactDto result = new ContactDto();
		result.setUserInfoId(getId());
		result.setUserIdFk(getUserIdFk());
		result.setCompany("nothing");
		result.setCustomerId(getCustomerId());
		result.setEmail(getEmail());
		result.setMainCity(getMailingCity());
		result.setMainName(getFirstName());
		result.setMainSurname(getLastName());
		result.setMainPoPox("");
		result.setMainStreet(getMailingStreet());
		result.setMainZipcode(getMailingPostalCode());

		return result;
	}

}

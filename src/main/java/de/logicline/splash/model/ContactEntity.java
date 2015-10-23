package de.logicline.splash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.logicline.splash.dto.ContactDto;

@Entity
public class ContactEntity {

	@Id
	@GeneratedValue
	@Column(nullable = false)
	private Integer id;

	private Integer userIdFk;

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
		result.setUserIdFk(getUserIdFk());
		result.setEmail(getEmail());
		result.setMainName(getFirstName());
		result.setMainSurname(getLastName());
		result.setMainCity(getMailingCity());
		result.setMainCountry(getMailingCountry());
		result.setMainZipcode(getMailingPostalCode());
		result.setMainStreet(getMailingStreet());
		result.setMainPhone(getPhone());

		return result;
	}

}

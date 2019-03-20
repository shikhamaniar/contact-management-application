package com.emxcel.contactmanagementapplication.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String profilepicture;
	private String firstName;
	private String middleName;
	private String lastName;
	private String number1;
	private String number2;
	private String number3;
	private String email;
	private String location;

	public String getProfilepicture() {
		return profilepicture;
	}

	public void setProfilepicture(String profilepicture) {
		this.profilepicture = profilepicture;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNumber1() {
		return number1;
	}

	public void setNumber1(String number1) {
		this.number1 = number1;
	}

	public String getNumber2() {
		return number2;
	}

	public void setNumber2(String number2) {
		this.number2 = number2;
	}

	public String getNumber3() {
		return number3;
	}

	public void setNumber3(String number3) {
		this.number3 = number3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Contact(String profilepicture, String firstName, String middleName, String lastName, String number1,
			String number2, String number3, String email, String location) {
		this.profilepicture = profilepicture;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
		this.email = email;
		this.location = location;
	}

	public Contact() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

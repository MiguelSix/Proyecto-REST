package com.itq.userService.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class User {
	
	private int userID;
	@NotBlank(message = "ERROR 400. Name is mandatory")
	@Length(max = 15, message = "ERROR 400. Name must be less than 15 characters")
	private String name;
	@NotBlank(message = "ERROR 400. Lastname is mandatory")
	@Length(max = 30, message = "ERROR 400. Lastname must be less than 30 characters")
	private String lastname;
	@Valid
	private Address address;
	@NotBlank(message = "ERROR 400. Phone is mandatory")
	@Pattern(regexp="[0-9]{10}", message = "ERROR 400. Phone must be 10 digits")
	private String phone;
	@NotBlank(message = "ERROR 400. Mail is mandatory")
	@Pattern(regexp="^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,})$", message = "ERROR 400. Mail must be a valid mail")
	private String mail;
	@NotBlank(message = "ERROR 400. RFC is mandatory")
	@Pattern(regexp="^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])[A-Z|\\d]{3})$", message = "ERROR 400. RFC must be a valid RFC")
	private String rfc;
	@NotBlank(message = "ERROR 400. Password is mandatory")
	@Pattern(regexp="^[a-zA-Z][a-zA-Z0-9_]{3,14}$", message = "ERROR 400. The password's first character must be a letter, it must contain at least 4 characters and no more than 15, and no characters other than letters, numbers and the underscore may be used")
	private String password;
	@NotBlank(message = "ERROR 400. Type is mandatory")
	@Pattern(regexp="^(Client|Provider)$", message = "Type must be Client or Provider")
	private String type;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}

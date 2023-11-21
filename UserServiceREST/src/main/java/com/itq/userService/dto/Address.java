package com.itq.userService.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	
	private int addressID;
	@NotBlank(message = "ERROR 400. Street is mandatory")
	@NotEmpty(message = "ERROR 400. Street is mandatory")
	@Length(max = 50, message = "ERROR 400. Street must be less than 50 characters")
	private String street;
	@NotEmpty(message = "ERROR 400. ExtNumber is mandatory")
	@NotBlank(message = "ERROR 400. ExtNumber is mandatory")
	@Length(max = 5, message = "ERROR 400. ExtNumber must be less than 5 characters")
	private int extNumber;
	@NotEmpty(message = "ERROR 400. IntNumber is mandatory")
	@NotBlank(message = "ERROR 400. IntNumber is mandatory")
	@Length(max = 5, message = "ERROR 400. IntNumber must be less than 5 characters")
	private int intNumber;
	@JsonProperty("suburb")
	@NotEmpty(message = "ERROR 400. Locality is mandatory")
	@NotBlank(message = "ERROR 400. Locality is mandatory")
	@Length(max = 50, message = "ERROR 400. Locality must be less than 50 characters")
	private String locality;
	@NotEmpty(message = "ERROR 400. ZipCode is mandatory")
	@NotBlank(message = "ERROR 400. ZipCode is mandatory")
	@Length(max = 10, message = "ERROR 400. ZipCode must be less than 10 characters")
	private int zipCode;
	@NotEmpty(message = "ERROR 400. City is mandatory")
	@NotBlank(message = "ERROR 400. City is mandatory")
	@Length(max = 25, message = "ERROR 400. City must be less than 25 characters")
	private String city;
	@NotEmpty(message = "ERROR 400. State is mandatory")
	@NotBlank(message = "ERROR 400. State is mandatory")
	@Length(max = 25, message = "ERROR 400. State must be less than 25 characters")
	private String state;
	@NotEmpty(message = "ERROR 400. Country is mandatory")
	@NotBlank(message = "ERROR 400. Country is mandatory")
	@Length(max = 25, message = "ERROR 400. Country must be less than 25 characters")
	private String country;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getExtNumber() {
		return extNumber;
	}
	public void setExtNumber(int extNumber) {
		this.extNumber = extNumber;
	}
	public int getIntNumber() {
		return intNumber;
	}
	public void setIntNumber(int intNumber) {
		this.intNumber = intNumber;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}

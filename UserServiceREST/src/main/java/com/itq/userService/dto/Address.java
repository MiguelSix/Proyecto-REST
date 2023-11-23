package com.itq.userService.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	
	@JsonProperty("addressId")
	private int addressID;
	@NotBlank(message = "ERROR 400. Street is mandatory")
	@Length(max = 50, message = "ERROR 400. Street must be less than 50 characters")
	private String street;
	@NotNull(message = "ERROR 400. Exterior Number is mandatory")
	@Min(value = 1, message = "ERROR 400. Exterior Number is mandatory & must be greater than 0")
	@JsonProperty("exteriorNumber")
	private int extNumber;
	@JsonProperty("interiorNumber")
	@NotNull(message = "ERROR 400. Interior Number is mandatory")
	@Min(value = 1, message = "ERROR 400. Interior Number is mandatory & must be greater than 0")
	private int intNumber;
	@JsonProperty("suburb")
	@NotEmpty(message = "ERROR 400. Suburb is mandatory")
	@NotBlank(message = "ERROR 400. Suburb is mandatory")
	@Length(max = 50, message = "ERROR 400. Locality must be less than 50 characters")
	private String locality;
	@NotNull(message = "ERROR 400. ZipCode is mandatory")
	@Digits(integer = 5, fraction = 0, message = "ERROR 400. ZipCode is mandatory & must be 5 digits")
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

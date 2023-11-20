package com.itq.SaleService.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

public class Sale {
	private int saleId;
	private int productId;
	private int providerId;
	private int clientId;
	@Length(max = 30, message = "Product name must be 30 characters or less")
	private String saleName;
	@PositiveOrZero(message = "Product price must be greater than or equal to 0")
	private float productPrice;
	@NotBlank(message = "Date is mandatory")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date should be in the format YYYY-MM-DD")
	private String date;
	@Min(value = 1, message = "Product quantity must be greater than 0")
	private int productQuantity;
	private float total;
	@NotBlank(message = "Status is mandatory")
	@Pattern(regexp = "^(Active|Inactive)$", message = "Status must be either Active or Inactive")
	private String status;
	@Pattern(regexp="^(Food|Clothes|Electronics|Home|Health|Beauty|Automotive|Shoes|Other)$", message="Sale category must be one of the following: Food, Clothes, Electronics, Home, Health, Beauty, Automotive, Shoes, Other")
    @JsonProperty("category")
    private String saleCategory;
	
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String productName) {
		this.saleName = productName;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSaleCategory() {
		return saleCategory;
	}
	public void setSaleCategory(String saleCategory) {
		this.saleCategory = saleCategory;
	}
	
	

	

}

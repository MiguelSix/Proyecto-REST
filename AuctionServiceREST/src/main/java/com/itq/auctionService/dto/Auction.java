package com.itq.auctionService.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Auction {
	
	private int auctionId;
	@Min(value = 1, message = "ERROR 400. Product id is mandatory & must be greater than 0")
	private int productId;
	private int clientId;
	@Min(value = 1, message = "ERROR 400. Provider id is mandatory & must be greater than 0")
	private int providerId;
	@DecimalMin(value = "0.0", inclusive = false, message = "ERROR 400. Initial price is mandatory & must be greater than 0")
	private double initialPrice;
	@DecimalMin(value = "0.0", inclusive = false, message = "ERROR 400. Final price is mandatory & must be greater than 0")
	private double finalPrice;
	@NotBlank(message = "ERROR 400. Auction date is mandatory")
	@Pattern(regexp = "([0-9]{4})-([0-9]{2})-([0-9]{2})", message = "ERROR 400. Auction date must be in the format yyyy-mm-dd")
	private String auctionDate;
	@NotBlank(message = "ERROR 400. Auction status is mandatory")
	@Pattern(regexp = "^(Active|Inactive)$", message = "ERROR 400. Auction status must be Active or Inactive")
	private String auctionStatus;

	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public double getInitialPrice() {
		return initialPrice;
	}
	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public String getAuctionDate() {
		return auctionDate;
	}
	public void setAuctionDate(String auctionDate) {
		this.auctionDate = auctionDate;
	}
	public String getAuctionStatus() {
		return auctionStatus;
	}
	public void setAuctionStatus(String auctionStatus) {
		this.auctionStatus = auctionStatus;
	}
	
}

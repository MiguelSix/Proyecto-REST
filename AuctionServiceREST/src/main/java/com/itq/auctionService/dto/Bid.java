package com.itq.auctionService.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Bid {

    private int bidId;
	@Min(value = 1, message = "ERROR 400. Auction id is mandatory & must be greater than 0")
    private int auctionId;
	@Min(value = 1, message = "ERROR 400. Client id is mandatory & must be greater than 0")
    private int clientId;
	@Min(value = 1, message = "ERROR 400. Provider id is mandatory & must be greater than 0")
    private int providerId;
	@Min(value = 1, message = "ERROR 400. Product id is mandatory & must be greater than 0")
    private int productId;
    @NotBlank(message = "ERROR 400. Date is mandatory")
    @Pattern(regexp = "([0-9]{4})-([0-9]{2})-([0-9]{2})", message = "ERROR 400. Date must be in the format yyyy-mm-dd")
    private String date;
    @DecimalMin(value = "0.0", inclusive = false, message = "ERROR 400. Price is mandatory & must be greater than 0")
    private double price;
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}

package com.auction.model;

public class ViewAmountPojo {
int userId;
String bidderName;
String productName;
int bidAmount;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getBidderName() {
	return bidderName;
}
public void setBidderName(String bidderName) {
	this.bidderName = bidderName;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getBidAmount() {
	return bidAmount;
}
public void setBidAmount(int bidAmount) {
	this.bidAmount = bidAmount;
}
public ViewAmountPojo(int userId, String bidderName, String productName, int bidAmount) {
	super();
	this.userId = userId;
	this.bidderName = bidderName;
	this.productName = productName;
	this.bidAmount = bidAmount;
}
public ViewAmountPojo()
{
	
}
@Override
public String toString() {
	return "ViewAmountPojo [userId=" + userId + ", bidderName=" + bidderName + ", productName=" + productName
			+ ", bidAmount=" + bidAmount + "]";
}

}

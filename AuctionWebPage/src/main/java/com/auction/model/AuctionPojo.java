package com.auction.model;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

import com.mysql.cj.jdbc.Blob;

public class AuctionPojo {
	String email,name,phoneNumber,password,productName,productModel,productCategory,productCondition,productDescription,terms;
	byte[] image;
	int averageAmount,maximumAmount,id,productId;
	Date startDate,endDate;
	public AuctionPojo(){
		
	}
	public String getEmail() {
		return email;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductModel() {
		return productModel;
	}
	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductCondition() {
		return productCondition;
	}
	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getAverageAmount() {
		return averageAmount;
	}
	public void setAverageAmount(int averageAmount) {
		this.averageAmount = averageAmount;
	}
	public int getMaximumAmount() {
		return maximumAmount;
	}
	public void setMaximumAmount(int maximumAmount) {
		this.maximumAmount = maximumAmount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public AuctionPojo(String email, String name, String phoneNumber, String password, String productName,
			String productModel, String productCategory, String productCondition, String productDescription,
			String terms, byte[] image, int averageAmount, int maximumAmount, int id, int productId, Date startDate,
			Date endDate) {
		super();
		this.email = email;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.productName = productName;
		this.productModel = productModel;
		this.productCategory = productCategory;
		this.productCondition = productCondition;
		this.productDescription = productDescription;
		this.terms = terms;
		this.image = image;
		this.averageAmount = averageAmount;
		this.maximumAmount = maximumAmount;
		this.id = id;
		this.productId = productId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public AuctionPojo(int userId, String userName, int productId, byte[] image2, String productModel2, String productCategory2, String productCondition2, String productDescription2, String terms2, int averageAmount2, int maximumAmount2, Date startDate2, Date endDate2) {
	    this.id = userId;
	    this.name = userName;
	    this.productId = productId;
	    this.image = image2;
	    this.productModel = productModel2;
	    this.productCategory = productCategory2;
	    this.productCondition = productCondition2;
	    this.productDescription = productDescription2;
	    this.terms = terms2;
	    this.averageAmount = averageAmount2;
	    this.maximumAmount = maximumAmount2;
	    this.startDate = startDate2;
	    this.endDate = endDate2;
	}


	@Override
	public String toString() {
		return "AuctionPojo [email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ", productName=" + productName + ", productModel=" + productModel + ", productCategory="
				+ productCategory + ", productCondition=" + productCondition + ", productDescription="
				+ productDescription + ", terms=" + terms + ", image=" + Arrays.toString(image) + ", averageAmount="
				+ averageAmount + ", maximumAmount=" + maximumAmount + ", id=" + id + ", productId=" + productId
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
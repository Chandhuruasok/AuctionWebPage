package com.auction.model;

import java.io.InputStream;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

import com.mysql.cj.jdbc.Blob;

public class AuctionPojo {
	String email,name,phoneNumber,password,productName,productModel,productCategory,productCondition,productDescription,terms;
	InputStream image;
	int averageAmount,maximumAmount;
	Date startDate,endDate;
	Time startTime,endTime;
	public String getEmail() {
		return email;
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

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream productImage) {
		this.image = productImage;
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

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	
	public AuctionPojo(String email, String name, String phoneNumber, String password, String productName,
			String productModel, String productCategory, String productCondition, String productDescription,
			String terms, InputStream image, int averageAmount, int maximumAmount, Date startDate, Date endDate,
			Time startTime, Time endTime) {
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
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public AuctionPojo()
	{
	
	}

	@Override
	public String toString() {
		return "AuctionPojo [email=" + email + ", name=" + name + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ", productName=" + productName + ", productModel=" + productModel + ", productCategory="
				+ productCategory + ", productCondition=" + productCondition + ", productDescription="
				+ productDescription + ", terms=" + terms + ", image=" + image + ", averageAmount=" + averageAmount
				+ ", maximumAmount=" + maximumAmount + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	

	
	}
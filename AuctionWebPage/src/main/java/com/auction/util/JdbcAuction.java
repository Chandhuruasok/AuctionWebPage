package com.auction.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.auction.model.AuctionPojo;



public class JdbcAuction {

public static boolean insert(AuctionPojo auctionPojo) throws ClassNotFoundException, SQLException {
        Connection connection = Util.getConnection();
        String query = "select email from e_auction where email=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, auctionPojo.getEmail());
        ResultSet result = preparedStatement.executeQuery();
        if (!result.next()) {
            String check ="insert into e_auction  (email,user_name,phone_number,user_password)values(?,?,?,?)";

            PreparedStatement prepare = connection.prepareStatement(check);
            prepare.setString(1, auctionPojo.getEmail());
            prepare.setString(2, auctionPojo.getName());
            prepare.setString(3, auctionPojo.getPhoneNumber());
            prepare.setString(4, auctionPojo.getPassword());
            prepare.execute();
            System.out.println("registered  successfully");
            return true;
        } 
        else
        {
       
       
            return false;
        }
		
        }
public static boolean login(AuctionPojo auctionPojo) throws ClassNotFoundException, SQLException {
    Connection connection = Util.getConnection();
    String query = "SELECT email,user_password FROM e_auction WHERE email=? AND user_password=?";
    PreparedStatement prepare = connection.prepareStatement(query);
    prepare.setString(1, auctionPojo.getEmail());
    prepare.setString(2, auctionPojo.getPassword());
    ResultSet result = prepare.executeQuery();
    if(result.next())
	{
		
		return true;
	}
	
       
		return false;
		
	
}
public static void insertProduct(AuctionPojo auctionPojo) throws ClassNotFoundException, SQLException {
    Connection connection = Util.getConnection();
    String query = "insert into user_product (name,product_name,image,product_model,product_caterogy,product_condition,product_description,terms_and_conditions,average_amount,maximum_amount,bid_start_date,bid_end_date,bid_start_time,bid_end_time)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement prepare = connection.prepareStatement(query);
    prepare.setString(1, auctionPojo.getName());
    prepare.setString(2, auctionPojo.getProductName());
    prepare.setBlob(3, auctionPojo.getImage());
    prepare.setString(4, auctionPojo.getProductModel());
    prepare.setString(5, auctionPojo.getProductCategory());
    prepare.setString(6, auctionPojo.getProductCondition());
    prepare.setString(7, auctionPojo.getProductDescription());
    prepare.setString(8, auctionPojo.getTerms());
    prepare.setInt(9, auctionPojo.getAverageAmount());
    prepare.setInt(10, auctionPojo.getMaximumAmount());
    prepare.setDate(11, auctionPojo.getStartDate());
    prepare.setDate(12, auctionPojo.getEndDate());
    prepare.setTime(13, auctionPojo.getStartTime());
    prepare.setTime(14, auctionPojo.getEndTime());
     prepare.executeUpdate();
    	
        
    
	
    }
}

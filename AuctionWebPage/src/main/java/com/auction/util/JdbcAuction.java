package com.auction.util;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
public AuctionPojo getId(AuctionPojo auctionPojo)throws ClassNotFoundException, SQLException {
	Connection connection = Util.getConnection();
    String query = "SELECT * from e_auction WHERE email=? ";
    PreparedStatement prepare = connection.prepareStatement(query);
    prepare.setString(1, auctionPojo.getEmail());
    ResultSet result = prepare.executeQuery();
    if(result.next())
	{
    	int id=result.getInt("id");
    	String name=result.getString("user_name");
		System.out.println(id);
		System.out.println(name);
		auctionPojo.setId(id);
		auctionPojo.setName(name);
		return auctionPojo;
	}
	
       
		return null;
		
}

public static int insertProduct(AuctionPojo auctionPojo) throws ClassNotFoundException, SQLException {
    Connection connection = Util.getConnection();
    String query = "insert into user_product (id,name,product_name,image,product_model,product_caterogy,product_condition,product_description,terms_and_conditions,average_amount,maximum_amount,bid_start_date,bid_end_date)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
    PreparedStatement prepare = connection.prepareStatement(query);
    prepare.setInt(1, auctionPojo.getId());
    prepare.setString(2, auctionPojo.getName());
    prepare.setString(3, auctionPojo.getProductName());
    prepare.setBytes(4, auctionPojo.getImage());
    prepare.setString(5, auctionPojo.getProductModel());
    prepare.setString(6, auctionPojo.getProductCategory());
    prepare.setString(7, auctionPojo.getProductCondition());
    prepare.setString(8, auctionPojo.getProductDescription());
    prepare.setString(9, auctionPojo.getTerms());
    prepare.setInt(10, auctionPojo.getAverageAmount());
    prepare.setInt(11, auctionPojo.getMaximumAmount());
    prepare.setDate(12, auctionPojo.getStartDate());
    prepare.setDate(13, auctionPojo.getEndDate());
    System.out.println("from insertProduct ...");
    int row =  prepare.executeUpdate();
    return row;
    }
public  ArrayList<AuctionPojo>  selectAllUsers() throws ClassNotFoundException, SQLException
{
	ArrayList<AuctionPojo>viewuser=new ArrayList<AuctionPojo>();
	Connection connection=Util.getConnection();
	String query="select * from user_product;";
	PreparedStatement ps=connection.prepareStatement(query);
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		
		int userId=rs.getInt("id");
		String userName=rs.getString("name");
		int productId=rs.getInt("product_id");
		Blob blob = rs.getBlob("image");
        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
		String productModel=rs.getString("product_model");
		String productCategory=rs.getString("product_caterogy");
		String productCondition=rs.getString("product_condition");
		String productDescription=rs.getString("product_description");
		String terms=rs.getString("terms_and_conditions");
		int averageAmount=rs.getInt("average_amount");
		int maximumAmount=rs.getInt("maximum_amount");
		Date startDate=rs.getDate("bid_start_date");
		Date endDate=rs.getDate("bid_end_date");
		viewuser.add(new AuctionPojo(userId,userName,productId,imageBytes,productModel,productCategory,productCondition,productDescription,terms,averageAmount,maximumAmount,startDate,endDate));
	}
	return viewuser;
	
	
}
}

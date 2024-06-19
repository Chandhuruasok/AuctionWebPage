package com.auction.util;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.auction.model.AuctionPojo;
import com.auction.model.BidPojo;
import com.auction.model.ViewAmountPojo;




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
	try
	{
    PreparedStatement prepare = connection.prepareStatement("select id,user_name from e_auction WHERE email=? ");
    prepare.setString(1, auctionPojo.getEmail());
    ResultSet result = prepare.executeQuery();
    if(result.next())
	{
    	int id=result.getInt("id");
    	String name=result.getString("user_name");
		auctionPojo.setId(id);
		auctionPojo.setName(name);
		return auctionPojo;
	}
	
	}
	catch (SQLException e) {
        
        e.printStackTrace();
    }
    
	
		return null;
		
}

public static int insertProduct(AuctionPojo auctionPojo) throws ClassNotFoundException, SQLException {
    Connection connection = Util.getConnection();
    String query = "insert into user_product (id,name,product_name,image,product_model,product_caterogy,product_condition,product_description,terms_and_conditions,average_amount,bid_start_date,bid_end_date,iso_certification)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
    prepare.setDate(11, auctionPojo.getStartDate());
    prepare.setDate(12, auctionPojo.getEndDate());
    prepare.setBytes(13, auctionPojo.getIso());
    
    
    return prepare.executeUpdate();
    }
public  ArrayList<AuctionPojo>  selectAllUsers() throws ClassNotFoundException, SQLException
{
	ArrayList<AuctionPojo>viewuser=new ArrayList<>();
	Connection connection=Util.getConnection();
	
	PreparedStatement ps=connection.prepareStatement("select * from user_product where is_approval=false;");
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		
		int userId=rs.getInt("id");
		
		String userName=rs.getString("name");
		int productId=rs.getInt("product_id");
		String productName=rs.getString("product_name");
		Blob blob = rs.getBlob("image");
        byte[] imageBytes = blob.getBytes(1, (int) blob.length());
        
		String productModel=rs.getString("product_model");
		String productCategory=rs.getString("product_caterogy");
		String productCondition=rs.getString("product_condition");
		String productDescription=rs.getString("product_description");
		String terms=rs.getString("terms_and_conditions");
		int averageAmount=rs.getInt("average_amount");
		Date startDate=rs.getDate("bid_start_date");
		Date endDate=rs.getDate("bid_end_date");
		Blob blob1 = rs.getBlob("iso_certification");
        byte[] isoBytes = blob1.getBytes(1, (int) blob1.length());
		viewuser.add(new AuctionPojo(userId,userName,productId,productName,imageBytes,productModel,productCategory,productCondition,productDescription,terms,averageAmount,startDate,endDate,isoBytes));
	
		
	}
	
	return viewuser;
}
public void deleteUser(int id) throws ClassNotFoundException, SQLException {
    
    
    Connection connection = Util.getConnection();
    String delete = "delete from user_product where product_id=?";
    PreparedStatement prepareStatement = connection.prepareStatement(delete);
    prepareStatement.setInt(1,id);
    prepareStatement.executeUpdate();
    
       
   
}
public void approveProduct(int id) throws SQLException, ClassNotFoundException {
    Connection connection = Util.getConnection();
    String sql = "update user_product set is_approval = true where id = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, id);
    statement.executeUpdate();
    
}
public ArrayList<AuctionPojo> getApprovedProduct() throws ClassNotFoundException, SQLException {
    ArrayList<AuctionPojo> approvedProducts = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        connection = Util.getConnection();
        preparedStatement = connection.prepareStatement("select * from user_product where is_approval = true");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        
        	AuctionPojo pojo = new AuctionPojo();
        	
        	pojo.setName(resultSet.getString("name"));
        	pojo.setProductName(resultSet.getString("product_name"));
        	pojo.setProductId(resultSet.getInt("product_id"));
        	Blob blob = resultSet.getBlob("image");
        	byte[] imageBytes = blob.getBytes(1, (int) blob.length());
        	pojo.setImage(imageBytes);
            pojo.setProductModel(resultSet.getString("product_model"));
            pojo.setProductCategory(resultSet.getString("product_caterogy"));
            pojo.setProductCondition(resultSet.getString("product_condition"));
            pojo.setProductDescription(resultSet.getString("product_description"));
            pojo.setTerms(resultSet.getString("terms_and_conditions"));
            pojo.setAverageAmount(resultSet.getInt("average_amount"));
            pojo.setStartDate(resultSet.getDate("bid_start_date"));
            pojo.setEndDate(resultSet.getDate("bid_end_date"));
            approvedProducts.add(pojo);
        }
    } finally {
        
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    return approvedProducts;
}
public  void insertBidWin(ViewAmountPojo viewAmountPojo,BidPojo bidPojo) throws ClassNotFoundException, SQLException {
    Connection connection = Util.getConnection();
    String query = "insert into bidder (id,bidder_name,product_name,bid_amount)values(?,?,?,?)";
    PreparedStatement prepare = connection.prepareStatement(query);
    prepare.setInt(1, viewAmountPojo.getUserId());
    prepare.setString(2, viewAmountPojo.getBidderName());
    prepare.setString(3, viewAmountPojo.getProductName());
    prepare.setInt(4, bidPojo.getBidAmount());
    prepare.executeUpdate();
   
    }
public static ArrayList<ViewAmountPojo> viewBidAmount() throws ClassNotFoundException, SQLException {
	Connection connection=Util.getConnection();
	ArrayList<ViewAmountPojo> viewBidAmount = new ArrayList<>();
	
	PreparedStatement ps=connection.prepareStatement("select * from bidder");
	ResultSet resultSet=ps.executeQuery();
    while (resultSet.next()) {
    
    	ViewAmountPojo viewAmountPojo = new ViewAmountPojo();
    	viewAmountPojo.setUserId(resultSet.getInt("id"));
    	viewAmountPojo.setBidderName(resultSet.getString("bidder_name"));
    	viewAmountPojo.setProductName(resultSet.getString("product_name"));
    	viewAmountPojo.setBidAmount(resultSet.getInt("bid_amount"));
    	viewBidAmount.add(viewAmountPojo);
    }
	return viewBidAmount;
}
public static ArrayList<ViewAmountPojo> viewBidders(String productName) throws ClassNotFoundException, SQLException {
	Connection connection=Util.getConnection();
	ArrayList<ViewAmountPojo> viewBidders = new ArrayList<>();
	try
	{
	PreparedStatement ps=connection.prepareStatement("select * from bidder where product_name=?");
	 ps.setString(1, productName);
	ResultSet resultSet=ps.executeQuery();
    while (resultSet.next()) {
    
    	ViewAmountPojo viewAmountPojo = new ViewAmountPojo();
    	viewAmountPojo.setUserId(resultSet.getInt("id"));
    	viewAmountPojo.setBidderName(resultSet.getString("bidder_name"));
    	viewAmountPojo.setProductName(resultSet.getString("product_name"));
    	viewAmountPojo.setBidAmount(resultSet.getInt("bid_amount"));
    	viewBidders.add(viewAmountPojo);
    	
    }
	}
	catch (SQLException e) {
        
        e.printStackTrace();
    }
    
	return viewBidders;
}
public void approveId(int id) throws SQLException, ClassNotFoundException {
    Connection connection = Util.getConnection();
    String sql = "update bidder set winner_status = true where id = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setInt(1, id);
    statement.executeUpdate();
    
}
public  ArrayList<ViewAmountPojo>  Losers() throws ClassNotFoundException, SQLException
{
	ArrayList<ViewAmountPojo>viewloser=new ArrayList<>();
	Connection connection=Util.getConnection();
	try
	{
	PreparedStatement ps=connection.prepareStatement("select id,bidder_name,product_name,bid_amount from bidder where winner_status=false;");
	ResultSet rs=ps.executeQuery();
	while(rs.next())
	{
		
		int userId=rs.getInt("id");
		String bidderName=rs.getString("bidder_name");
		String productName=rs.getString("product_name");
		int bidAmount=rs.getInt("bid_amount");
		viewloser.add(new ViewAmountPojo(userId,bidderName,productName,bidAmount));
	
	}
	}
	catch (SQLException e) {
        
        e.printStackTrace();
    }
    
	return viewloser;
	
	
}
public ArrayList<ViewAmountPojo> getApprovedId() throws ClassNotFoundException, SQLException {
    ArrayList<ViewAmountPojo> approvedId = new ArrayList<>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
        connection = Util.getConnection();
        preparedStatement = connection.prepareStatement("select id,bidder_name,product_name,bid_amount from bidder where winner_status = true");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
        
        	ViewAmountPojo pojo = new ViewAmountPojo();
        	pojo.setUserId(resultSet.getInt("id"));
        	pojo.setBidderName(resultSet.getString("bidder_name"));
         	pojo.setProductName(resultSet.getString("product_name"));
        	pojo.setBidAmount(resultSet.getInt("bid_amount"));
        	approvedId.add(pojo);
        }
    } finally {
        
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    return approvedId;
}
public ArrayList<AuctionPojo> searchProductsByName(String productName) throws ClassNotFoundException {
    ArrayList<AuctionPojo> productList = new ArrayList<>();
    try (Connection connection = Util.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement("select product_caterogy from user_product where product_caterogy like ?"))
    {

        preparedStatement.setString(1,productName );
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            AuctionPojo product = extractProductFromResultSet(resultSet);
            productList.add(product);
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return productList;
}
private AuctionPojo extractProductFromResultSet(ResultSet resultSet) throws SQLException {
    AuctionPojo product = new AuctionPojo();
    
    product.setName(resultSet.getString("name"));
    product.setProductName(resultSet.getString("product_name"));
    product.setProductModel(resultSet.getString("product_model"));
    product.setProductCategory(resultSet.getString("product_caterogy"));
    product.setProductCondition(resultSet.getString("product_condition"));
    product.setProductDescription(resultSet.getString("product_description"));
    product.setTerms(resultSet.getString("terms_and_conditions"));
    product.setAverageAmount(resultSet.getInt("average_amount"));
    product.setStartDate(resultSet.getDate("bid_start_date"));
    product.setEndDate(resultSet.getDate("bid_end_date"));
    byte[] imageBytes = resultSet.getBytes("image");
    if (imageBytes != null) {
        product.setImage(imageBytes);
    }
    return product;
}
}
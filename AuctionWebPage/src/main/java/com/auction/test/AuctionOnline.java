package com.auction.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.auction.model.AuctionPojo;
import com.auction.util.JdbcAuction;
import java.sql.Blob;
@WebServlet("/AuctionOnline")
public class AuctionOnline extends HttpServlet {
    private static final long serialVersionUID = 1L;
    AuctionPojo auctionPojo = new AuctionPojo();
    JdbcAuction jdbcAuction = new JdbcAuction();

    public AuctionOnline() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phonenumber");
        String password = request.getParameter("password");
        String productName=request.getParameter("product_name");
        InputStream productImage=null;
        Part filePart=request.getPart("image");
        if(filePart!=null)
        { 
        	productImage = filePart.getInputStream();
        	
        }
        String productModel=request.getParameter("product_model");
        String productCategory=request.getParameter("product_category");
        String productCondition=request.getParameter("product_condition");
        String productDescription=request.getParameter("product_description");
        String terms=request.getParameter("terms_and_conditions");
        Date startDate = null;
        Date endDate = null;
        String bidStartDate = request.getParameter("bid_start_date");
        String bidEndDate = request.getParameter("bid_end_date");
        try {
            startDate = Date.valueOf(bidStartDate);
            endDate = Date.valueOf(bidEndDate);
            } catch (IllegalArgumentException e) {
            e.printStackTrace();
            }
        Time startTime = null;
        Time endTime = null;
        String bidStartTime = request.getParameter("bid_start_time");
        String bidEndTime = request.getParameter("bid_end_time");
        try {
            startTime = Time.valueOf(bidStartTime);
            endTime = Time.valueOf(bidEndTime);
            } catch (IllegalArgumentException e) {
            e.printStackTrace();
            }
        String averageAmount1 = request.getParameter("average_amount");
        String maximumAmount1 = request.getParameter("maximum_amount");
        if (averageAmount1 != null && !averageAmount1.isEmpty() && maximumAmount1!= null && !maximumAmount1.isEmpty()) {
            int averageAmount2 = Integer.parseInt(averageAmount1);
            int maximumAmount2 = Integer.parseInt(maximumAmount1);
            
            auctionPojo.setAverageAmount(averageAmount2);
            auctionPojo.setMaximumAmount(maximumAmount2);
        }
        auctionPojo.setEmail(email);
        auctionPojo.setName(name);
        auctionPojo.setPhoneNumber(phoneNumber);
        auctionPojo.setPassword(password);
        auctionPojo.setProductName(productName);
        auctionPojo.setImage(productImage);
        auctionPojo.setProductModel(productModel);
        auctionPojo.setProductCategory(productCategory);
        auctionPojo.setProductCondition(productCondition);
        auctionPojo.setProductDescription(productDescription);
        auctionPojo.setTerms(terms);
        
        auctionPojo.setStartDate(startDate);
        auctionPojo.setEndDate(endDate);
        auctionPojo.setStartTime(startTime);
        auctionPojo.setEndTime(endTime);
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            switch (action) {
                case "register":
                    
                       
				try {
					if(JdbcAuction.insert(auctionPojo))
					{
						response.sendRedirect("login.jsp");
					}
					else
					{
						response.sendRedirect("login.jsp");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                   break;     
                            
                case "login":
                    try {
                       
                        if(JdbcAuction.login(auctionPojo))
                        {
                            if(email.endsWith("@bidderboy.com")) 
                            {
                              response.sendRedirect("viewproduct.jsp");
                            }
                            else if(email.endsWith("@user.com"))
                            {
                            	  response.sendRedirect("userproduct.jsp");
                            }
                            else if(email.endsWith("@gmail.com"))
                            {
                            	  response.sendRedirect("bid.jsp");
                            }
                        }
                        else {
                           
                            response.sendRedirect("register.jsp");
                        }
                        
                    }
                     catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "user":
                    
                    
    				try {
    					 	

    	                    
    					JdbcAuction.insertProduct(auctionPojo);
    					
    					
    						
    					
    				 
    				}catch (ClassNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				response.sendRedirect("homepage.jsp");
                       break; 
                 
            
        }
    }
}
}
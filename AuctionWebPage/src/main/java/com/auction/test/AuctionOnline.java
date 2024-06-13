package com.auction.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.auction.model.AuctionPojo;
import com.auction.util.JdbcAuction;



@MultipartConfig
@WebServlet("/AuctionOnline")
public class AuctionOnline extends HttpServlet {
    private static final long serialVersionUID = 1L;
    AuctionPojo auctionPojo = new AuctionPojo(0, null, 0, null,null, null, null, null, null, null, 0, 0, null, null,null);
    JdbcAuction jdbcAuction = new JdbcAuction();

    public AuctionOnline() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        
        
        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null) {
            switch (action) {
                case "register":
                	String email = request.getParameter("email");
                    String name = request.getParameter("name");
                    String phoneNumber = request.getParameter("phonenumber");
                    String password = request.getParameter("password");
                    
                    auctionPojo.setEmail(email);
                    auctionPojo.setName(name);
                    auctionPojo.setPhoneNumber(phoneNumber);
                    auctionPojo.setPassword(password);
                       
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
                	String email1 = request.getParameter("email");
                    String name1 = request.getParameter("name");
                    String password1 = request.getParameter("password");
                    auctionPojo.setEmail(email1);
                    auctionPojo.setName(name1);
                    auctionPojo.setPassword(password1);
                    try {
                       
                        if(JdbcAuction.login(auctionPojo))
                        {
                        	AuctionPojo id=jdbcAuction.getId(auctionPojo);
                        	
                        	HttpSession session=request.getSession();
                        	System.out.println(id);
                        	session.setAttribute("userid",id);
                            if(email1.endsWith("@bidderboy.com")) 
                            {
                              response.sendRedirect("admin.jsp");
                            }
                            else if(email1.endsWith("@user.com"))
                            {
                            	  response.sendRedirect("userproduct.jsp");
                            }
                            else if(email1.endsWith("@gmail.com"))
                            {
                            	  response.sendRedirect("bidderviewproduct.jsp");
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
                	 int id=Integer.parseInt(request.getParameter("id"));

                	String name2 = request.getParameter("name");
                	String productName=request.getParameter("product_name");
                	Part filePart = request.getPart("image");
                	InputStream inputStream=null;
                    if (filePart != null) {
                       
                       
                        
                        
                        inputStream = filePart.getInputStream();
                    }
                    byte[] images =null;
                    if(inputStream !=null) {
                        images = inputStream.readAllBytes();
                    }
                    Part filePart1 = request.getPart("myfile");
                	InputStream inputStream1=null;
                    if (filePart1 != null) {
                       
                                     
                        
                        inputStream1 = filePart1.getInputStream();
                    }
                    byte[] iso =null;
                    if(inputStream1 !=null) {
                        iso = inputStream1.readAllBytes();
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
                    
                    String averageAmount1 = request.getParameter("average_amount");
                    String maximumAmount1 = request.getParameter("maximum_amount");
                    if (averageAmount1 != null && !averageAmount1.isEmpty() && maximumAmount1!= null && !maximumAmount1.isEmpty()) {
                        int averageAmount2 = Integer.parseInt(averageAmount1);
                        int maximumAmount2 = Integer.parseInt(maximumAmount1);
                        
                        auctionPojo.setAverageAmount(averageAmount2);
                        auctionPojo.setMaximumAmount(maximumAmount2);
                    }
                    auctionPojo.setId(id);
                    auctionPojo.setName(name2);
                    auctionPojo.setProductName(productName);
                    auctionPojo.setImage(images);
                    auctionPojo.setProductModel(productModel);
                    auctionPojo.setProductCategory(productCategory);
                    auctionPojo.setProductCondition(productCondition);
                    auctionPojo.setProductDescription(productDescription);
                    auctionPojo.setTerms(terms);
                    
                    auctionPojo.setStartDate(startDate);
                    auctionPojo.setEndDate(endDate);
                    auctionPojo.setIso(iso);
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
                case "delete":
                	int deleteNo=Integer.parseInt(request.getParameter("deleteproductid"));
                    try {
                        
                        JdbcAuction user=new JdbcAuction();
                        
                        user.deleteUser(deleteNo);
                    } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                        
                    }
                    response.sendRedirect("admin.jsp");
                    break;

        }
    }
}
}
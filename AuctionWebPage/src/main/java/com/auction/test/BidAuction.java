package com.auction.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auction.model.AuctionPojo;
import com.auction.model.BidPojo;
import com.auction.model.ViewAmountPojo;
import com.auction.util.JdbcAuction;

/**
 * Servlet implementation class BidAuction
 */
@WebServlet("/BidAuction")
public class BidAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JdbcAuction jdbcAuction = new JdbcAuction(); 
	static AuctionPojo auctionPojo=new AuctionPojo();
	static ViewAmountPojo viewAmountPojo=new ViewAmountPojo();
	static BidPojo bidPojo=new BidPojo();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidAuction() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    String productName =request.getParameter("productname");
	    

	   
	    request.setAttribute("productName", productName);
        request.getRequestDispatcher("adminviewbidders.jsp").forward(request, response);

	    
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
    	
	    int userId = Integer.parseInt(request.getParameter("userId"));
	    
	    String bidderName = request.getParameter("biddername");
    	
	    String productName = request.getParameter("productname");
	    int bidAmount = Integer.parseInt((request.getParameter("bidAmount")));
	    
	    viewAmountPojo.setUserId(userId);
	    viewAmountPojo.setBidderName(bidderName);
	    viewAmountPojo.setProductName(productName);
	    bidPojo.setBidAmount(bidAmount);

	    try {
	        JdbcAuction jdbcAuction1 = new JdbcAuction();
	        jdbcAuction1.insertBidWin(viewAmountPojo,bidPojo);
	        
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        
	    }
	    response.sendRedirect("bidderviewproduct.jsp");
	}
    

}

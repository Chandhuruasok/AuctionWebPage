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
	JdbcAuction jdbcAuction = new JdbcAuction(); 
	AuctionPojo auctionPojo=new AuctionPojo();
	ViewAmountPojo viewAmountPojo=new ViewAmountPojo();
	 BidPojo bidPojo=new BidPojo();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidAuction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    String productName =request.getParameter("productname");
	    

	   
	    System.out.println(productName);
	    

	    request.setAttribute("productName", productName);
        request.getRequestDispatcher("viewbidders.jsp").forward(request, response);

	    
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int userId = Integer.parseInt(request.getParameter("userId"));
	    String bidderName = request.getParameter("biddername");
	    String productName = request.getParameter("productname");
	    int bidAmount = Integer.parseInt((request.getParameter("bidAmount")));

	    viewAmountPojo.setUserId(userId);
	    viewAmountPojo.setBidderName(bidderName);
	    viewAmountPojo.setProductName(productName);
	    bidPojo.setBidAmount(bidAmount);

	    try {
	        JdbcAuction jdbcAuction = new JdbcAuction();
	        jdbcAuction.insertBidWin(viewAmountPojo,bidPojo);
	        
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        
	    }
	    response.sendRedirect("homepage.jsp");
	}

}

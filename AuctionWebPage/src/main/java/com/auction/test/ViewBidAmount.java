package com.auction.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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
 * Servlet implementation class ViewBidAmount
 */
@WebServlet("/ViewBidAmount")
public class ViewBidAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcAuction jdbcAuction=new JdbcAuction(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBidAmount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<ViewAmountPojo> viewBidAmount = null;
		try {
        	viewBidAmount = jdbcAuction.viewBidAmount();
        	
} 	
        catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	}
        System.out.println("The value is"+ viewBidAmount);

        request.setAttribute("viewBidAmount", viewBidAmount);


        request.getRequestDispatcher("viewbidamount.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}

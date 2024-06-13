package com.auction.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auction.model.AuctionPojo;
import com.auction.model.BidPojo;
import com.auction.util.JdbcAuction;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static JdbcAuction jdbcAuction = new JdbcAuction(); 
	 static AuctionPojo auctionPojo=new AuctionPojo();
	 static BidPojo bidPojo=new BidPojo();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
    @Override
	protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		ArrayList<AuctionPojo> approvedProducts = null;
        try {
        	approvedProducts = jdbcAuction.getApprovedProduct();
} 
        catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	}
        

        request.setAttribute("approvedProducts", approvedProducts);


        request.getRequestDispatcher("approvaladmin.jsp").forward(request, response);
}
	     
	       

	        
	    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		   		
	int approveNo=Integer.parseInt(request.getParameter("approveid"));
	if(approveNo!=0)
	{
    try {
    	PrintWriter out=response.getWriter();
		out.println("approval"+approveNo);
    	
        
        JdbcAuction user=new JdbcAuction();
        
        user.approveProduct(approveNo);
    } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        
    }
	}
    response.sendRedirect("admin.jsp");
    
    
}
	}
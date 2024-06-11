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
import com.auction.util.JdbcAuction;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 JdbcAuction jdbcAuction = new JdbcAuction(); 
	 AuctionPojo auctionPojo=new AuctionPojo();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		ArrayList<AuctionPojo> approvedProducts = null;
        try {
        	approvedProducts = jdbcAuction.getApprovedProduct();
} 
        catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	}
        System.out.println("The value is"+ approvedProducts);

        request.setAttribute("approvedProducts", approvedProducts);


        request.getRequestDispatcher("ApprovalAdmin.jsp").forward(request, response);
}
	     
	       

	        
	    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		   		
	int approveNo=Integer.parseInt(request.getParameter("approveid"));
	if(approveNo!=0)
	{
    try {
    	System.out.println("approval"+approveNo);
        
        JdbcAuction user=new JdbcAuction();
        
        user.approveProduct(approveNo);
    } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        
    }
	}
    response.sendRedirect("admin.jsp");

}
	}

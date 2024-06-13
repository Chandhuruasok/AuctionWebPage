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
import com.auction.model.ViewAmountPojo;
import com.auction.util.JdbcAuction;

/**
 * Servlet implementation class Winner
 */
@WebServlet("/Winner")
public class Winner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JdbcAuction Jdbcauction=new JdbcAuction();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Winner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<ViewAmountPojo> approvedIds = null;
        try {
        	approvedIds = Jdbcauction.getApprovedId();
} 
        catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	}
        

        request.setAttribute("approvedIds", approvedIds);


        request.getRequestDispatcher("approvaladmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		int approveId=Integer.parseInt(request.getParameter("approveid"));
		if(approveId!=0)
		{
	    try {
	    	
	        
	        JdbcAuction user=new JdbcAuction();
	        
	        user.approveId(approveId);
	    } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        
	    }
		}
	    response.sendRedirect("loserview.jsp");
	}

}

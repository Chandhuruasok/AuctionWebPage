package com.auction.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.auction.model.ViewAmountPojo;
import com.auction.util.JdbcAuction;

/**
 * Servlet implementation class Winner
 */
@WebServlet("/Winner")
public class Winner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JdbcAuction Jdbcauction=new JdbcAuction();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Winner() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<ViewAmountPojo> approvedIds = null;
        try {
        	approvedIds = Jdbcauction.getApprovedId();
} 
        catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	}
        

        request.setAttribute("approvedIds", approvedIds);


        request.getRequestDispatcher("winnerview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		int approveId=Integer.parseInt(request.getParameter("approveid"));
		if(approveId!=0)
		{
	    try {
	    	
	        
	        JdbcAuction user=new JdbcAuction();
	        
	        user.approveId(approveId);
	        response.sendRedirect("loserview.jsp");
	    } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        
	    }
		}
	   
	}

}

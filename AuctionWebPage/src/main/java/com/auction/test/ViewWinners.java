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
 * Servlet implementation class ViewWinners
 */
@WebServlet("/ViewWinners")
public class ViewWinners extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JdbcAuction jdbcAuction = new JdbcAuction();
	static ViewAmountPojo viewAmountPojo=new ViewAmountPojo();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewWinners() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String productName = request.getParameter("productname");
		
		request.setAttribute("productName", productName);
        request.getRequestDispatcher("viewwinners.jsp").forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String bidderName = request.getParameter("bidderName");
        String accountNumber = request.getParameter("accountNumber");
        int bidderId = Integer.parseInt(request.getParameter("userId"));
        String productName = request.getParameter("accountNumber");
       
        ViewAmountPojo viewAmountPojo = new ViewAmountPojo();
        viewAmountPojo.setBidderName(bidderName);
        viewAmountPojo.setBidderAccountNumber(accountNumber);
       
        viewAmountPojo.setUserId(bidderId);
      
        JdbcAuction jdbcAuction = new JdbcAuction();
        try {
            jdbcAuction.payment(viewAmountPojo); 
            jdbcAuction.successPayment(bidderId); 
           
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            
        }

       
        response.sendRedirect("viewwinners.jsp");
    }

}

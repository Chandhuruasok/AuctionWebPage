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
 * Servlet implementation class AdminViewWinner
 */
@WebServlet("/AdminViewWinner")
public class AdminViewWinner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static JdbcAuction jdbcAuction=new JdbcAuction();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminViewWinner() {
        super();

        

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    }
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<ViewAmountPojo> approvedIds = null;
        try {
        	approvedIds = jdbcAuction.getApprovedId();
} 
        catch (ClassNotFoundException | SQLException e) {
        	e.printStackTrace();
        	}
        

        request.setAttribute("approvedIds", approvedIds);


        request.getRequestDispatcher("adminviewwinner.jsp").forward(request, response);
	}
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

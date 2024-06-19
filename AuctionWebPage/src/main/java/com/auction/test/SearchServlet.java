package com.auction.test;

import com.auction.model.AuctionPojo;
import com.auction.util.JdbcAuction;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("search");
        
        if (searchQuery != null && !searchQuery.isEmpty()) {
            JdbcAuction jdbcAuction = new JdbcAuction();
            ArrayList<AuctionPojo> searchResults=null;
			try {
				searchResults = jdbcAuction.searchProductsByName(searchQuery);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
            
            request.setAttribute("searchResults", searchResults);
            request.getRequestDispatcher("bidderviewproduct.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "bidderviewproduct.jsp");
        }
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    throw new UnsupportedOperationException("POST method is not supported by this servlet.");
	}

}

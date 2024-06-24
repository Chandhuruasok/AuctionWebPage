<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.auction.model.ViewAmountPojo"%>
<%@ page import="com.auction.util.JdbcAuction"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.auction.model.AuctionPojo" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bidderyboy Auction Website - View Winners</title>
    <style>
        /* Your CSS styles */
        body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f0f0f0;
    display: flex;
    flex-direction: column;
}
header {
background-color: #333;
color: white;
padding: 20px;
text-align: center;
}

h1 {
margin: 0;
font-size: 24px;
}

nav {
background-color: #555;
color: white;
padding: 20px;
min-height: 100px;
}

nav ul {
list-style-type: none;
padding: 0;
margin: 0;
}

nav ul li {
margin-bottom: 10px;
}

nav ul li a {
color: white;
text-decoration: none;
font-size: 16px;
}

section {
padding: 20px;
flex-grow: 1;
}

table {
border-collapse: collapse;
width: 100%;
background-color: white;
border-radius: 5px;
overflow: hidden;
}

th, td {
border: 1px solid #ddd;
padding: 10px;
text-align: left;
}

.product-image {
max-width: 100px;
max-height: 100px;
object-fit: contain;
}

footer {
background-color: #333;
color: white;
padding: 10px;
text-align: center;
position: fixed;
bottom: 0;
width: 100%;
}

.form-container {
display: none;
background-color: #f9f9f9;
padding: 20px;
border-radius: 5px;
margin-top: 10px;
}

.form-container input {
width: calc(100% - 16px);
padding: 8px;
margin-bottom: 10px;
box-sizing: border-box;
border-radius: 4px;
border: 1px solid #ccc;
}

.form-container button {
width: 100%;
padding: 8px;
margin-top: 10px;
}
    </style>
</head>
<body>
    <header>
        <h1>View Winners</h1>
        <nav>
            <ul>
                
                <li><a href="login.jsp">Logout</a></li>
            </ul>
        </nav>
    </header>
    <section id="view-users">
        <table border="1">
            <thead>
                <th>Bidder Name</th>
                <th>Product Name</th>
                <th>Bid Amount</th>
                <th>Payment</th>
            </thead>
            <tbody>
                <% 
                    JdbcAuction jdbcAuction = new JdbcAuction();
                    String product = (String) request.getAttribute("productName");
                    ArrayList<ViewAmountPojo> array = jdbcAuction.viewWinners(product);
                    for (ViewAmountPojo pojo : array) {
                %>
                
                <tr>
                    <td><%= pojo.getBidderName() %></td>
                    <td><%= pojo.getProductName() %></td>
                    <td><%= pojo.getBidAmount() %></td>
                    <td>
                        <button onclick="showPaymentForm('<%= pojo.getBidderName() %>')">Pay</button>
                    </td>
                </tr>
                <div id="paymentForm_<%= pojo.getBidderName() %>" class="form-container">
                <%AuctionPojo user=(AuctionPojo)session.getAttribute("userid") ; %>
                    <form id="payForm_<%= pojo.getBidderName() %>" action="ViewWinners" method="post">
                        <label for="accountNumber">Account Number:</label>
                        <input type="text" id="accountNumber_<%= pojo.getBidderName() %>" name="accountNumber" placeholder="Enter the 16 digits number" pattern="[0-9]{16}" required>
                       
                        <label for="cvvNumber">CVV Number:</label>
                        <input type="text" id="cvvNumber_<%= pojo.getBidderName() %>" name="cvvNumber" placeholder="Enter the 3 digits number" pattern=[0-9]{3} required>
                        <label for="expiryDate">Expiry Date:</label>
                        <input type="text" id="expiryDate_<%= pojo.getBidderName() %>" name="expiryDate" placeholder="YYYY/MM"required>
                        <input type="hidden" id="bidderName_<%= pojo.getBidderName() %>" name="bidderName" value="<%= pojo.getBidderName() %>">
                        <input type="hidden" id="productName_<%= pojo.getBidderName() %>" name="productName" value="<%= pojo.getProductName() %>">
                        <input type="hidden" name="userId" value="<%=user.getId() %>">
                        <button type="submit">Pay Now</button>
                    </form>
                </div>
                <% 
                    }
                %>
            </tbody>
        </table>
    </section>

    <div id="paymentSuccessMessage" style="display: none; background-color: #4CAF50; color: white; text-align: center; padding: 10px;">
        Payment Successful!
    </div>

    <script>
        function showPaymentForm(bidderName) {
            var paymentForm = document.getElementById('paymentForm_' + bidderName);
            if (paymentForm.style.display === 'none' || paymentForm.style.display === '') {
                paymentForm.style.display = 'block';
            } else {
                paymentForm.style.display = 'none';
            }
        }
    </script>
</body>
</html>

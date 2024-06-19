<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.auction.model.ViewAmountPojo"%>
<%@ page import="com.auction.model.AuctionPojo"%>
<%@ page import="com.auction.util.JdbcAuction"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Bidderyboy Auction Website - View Winners</title>
<style>
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
                <li><a href="homepage.jsp">Home</a></li>
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
                    JdbcAuction user = new JdbcAuction();
                    String product = (String) request.getAttribute("productName");
                    ArrayList<ViewAmountPojo> array = user.viewWinners(product);
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
                    <form id="payForm_<%= pojo.getBidderName() %>" action="ProcessPayment" method="post">
                        <label for="accountNumber">Account Number:</label>
                        <input type="text" id="accountNumber_<%= pojo.getBidderName() %>" name="accountNumber" required>
                        <label for="ifscCode">IFSC Code:</label>
                        <input type="text" id="ifscCode_<%= pojo.getBidderName() %>" name="ifscCode" required>
                        <label for="cvvNumber">CVV Number:</label>
                        <input type="text" id="cvvNumber_<%= pojo.getBidderName() %>" name="cvvNumber" required>
                        <label for="expiryDate">Expiry Date:</label>
                        <input type="text" id="expiryDate_<%= pojo.getBidderName() %>" name="expiryDate" required>
                        <input type="hidden" id="bidderName_<%= pojo.getBidderName() %>" name="bidderName" value="<%= pojo.getBidderName() %>">
                        <input type="hidden" id="productName_<%= pojo.getBidderName() %>" name="productName" value="<%= pojo.getProductName() %>">
                        <button type="submit" onclick="submitPaymentForm(event, '<%= pojo.getBidderName() %>')">Pay Now</button>
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

        function submitPaymentForm(event, bidderName) {
            event.preventDefault(); // Prevent the form from submitting normally

            // Simulate payment success (you will handle this in your server-side code)
            var paymentSuccess = true; // Change this based on actual payment response

            if (paymentSuccess) {
                var paymentForm = document.getElementById('paymentForm_' + bidderName);
                paymentForm.style.display = 'none'; // Hide the payment form

                var successMessage = document.getElementById('paymentSuccessMessage');
                successMessage.style.display = 'block'; // Show the success message
            }
        }
    </script>
</body>
</html>

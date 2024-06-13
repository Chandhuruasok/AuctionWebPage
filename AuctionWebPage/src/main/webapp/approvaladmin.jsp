<%@ page import="com.auction.model.AuctionPojo" %>
<%@ page import="com.auction.util.JdbcAuction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bidderyboy Auction Website</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }
    header {
      background-color: green;
      color: white;
      font-size: 10px;
      padding: 2px;
      text-align: center;
      position: fixed;
      top: 0;
      width: 100%;
    }
    nav {
      background-color: #f4f4f4;
      padding: 10px;
    }
    nav ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
    }
    nav ul li {
      display: inline;
      margin-right: 20px;
    }
    section {
      padding: 20px;
    }
    .card-container {
      display: flex;
      flex-wrap: wrap;
      gap: 30px;
      justify-content: center;
    }
    .card {
      border: 2px solid #ccc;
      border-radius: 5px;
      padding: 10px;
      margin: 20px;
      background-color: pink;
      width: 350px;
    }
    .card img {
      display: block;
      margin: auto; 
      max-width: 80%;
      height:auto;
      border-radius: 4px;
    }
    table {
      border-collapse: collapse;
      width: 100%;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }
    footer {
      background-color: green;
      color: white;
      padding: 1px;
      text-align: center;
      position: fixed;
      bottom: 0;
      width: 100%;
    }
    .card button {
      width: 20%;
      height: 3%;
    }
    .bid-form label {
      display: block;
      margin-bottom: 10px;
    }
    .bid-form input[type="number"] {
      width: 100%;
      padding: 8px;
      margin-bottom: 10px;
      box-sizing: border-box;
      border-radius: 4px;
      border: 1px solid #ccc;
    }
    .countdown {
      margin-top: 10px;
      font-weight: bold;
    }
  </style>
</head>
<body>
  <header>
    <h1>Welcome!</h1>
  </header>
  <nav>
    <ul>
      <li><a href="homepage.jsp">Home</a></li>
        <li><a href="Admin">Approved Products</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="about.jsp">About</a></li>
    </ul>
  </nav>
  <section id="view-users">
    <center><h2>Approved User Products</h2></center>
    <div class="card-container">
        <%         
            JdbcAuction jdbcAuction = new JdbcAuction();
            ArrayList<AuctionPojo> approvedProducts = (ArrayList<AuctionPojo>) request.getAttribute("approvedProducts");
            if (approvedProducts != null && !approvedProducts.isEmpty()) {
                for (AuctionPojo pojo : approvedProducts) {
                    String base64Image = "";
                    if (pojo.getImage() != null) {
                        base64Image = Base64.getEncoder().encodeToString(pojo.getImage());
                    }
        %>
        <div class="card">
            <img class="product-image" src="data:image/jpeg;base64,<%=base64Image%>" alt="Image">
            <p>User ID: <%= pojo.getId() %></p>
            <p>User Name: <%= pojo.getName() %></p>
            <p>Product Id: <%= pojo.getProductId() %></p>
            <p>Product Name: <%= pojo.getProductName() %></p>
            <p>Product Model: <%= pojo.getProductModel() %></p>
            <p>Product Category: <%= pojo.getProductCategory() %></p>
            <p>Product Condition: <%= pojo.getProductCondition() %></p>
            <p>Product Description: <%= pojo.getProductDescription() %></p>
            <p>Terms and Condition : <%= pojo.getTerms() %></p>
            <p>Starting Bid Amount : <%= pojo.getAverageAmount() %></p>
            <p>Maximum Bid Amount : <%= pojo.getMaximumAmount() %></p>
            <p>Bid Start Date: <%= pojo.getStartDate() %></p>
            <p>Bid End Date: <%= pojo.getEndDate() %></p>
          
            <div class="countdown" id="countdown_<%= pojo.getProductId() %>"></div>
            <button type="button" onclick="toggleBidForm('<%= pojo.getProductId() %>')" title="bid">Bid Now</button>
            <% 
    AuctionPojo userId=(AuctionPojo)session.getAttribute("userid"); 
    
    %>
    <input type="hidden" value="<%=userId.getId()%>" name="id">
            <form id="bidForm_<%= pojo.getProductId() %>" class="bid-form" style="display: none;" action="BidAuction" method="post">
                <input type="hidden" name="userId" value="<%= userId.getId() %>">
                <input type="hidden" name="biddername" value="<%= userId.getName() %>">
                <input type="hidden" name="productname" value="<%= pojo.getProductName() %>">
                <label for="bidAmount_<%= pojo.getProductId() %>">Enter Bid Amount:</label>
                <input type="number" id="bidAmount_<%= pojo.getProductId() %>" name="bidAmount" required>
                <button type="submit">Submit Bid</button>
          
            </form>
        </div>
        <script>
          
          var startDate_<%= pojo.getProductId() %> = new Date('<%= pojo.getStartDate() %>').getTime();
          var endDate_<%= pojo.getProductId() %> = new Date('<%= pojo.getEndDate() %>').getTime();
          var countdownElement_<%= pojo.getProductId() %> = document.getElementById('countdown_<%= pojo.getProductId() %>');
          
          var countdownInterval_<%= pojo.getProductId() %>;

          function updateCountdown_<%= pojo.getProductId() %>() {
            var now = new Date().getTime();
            var distanceStart = startDate_<%= pojo.getProductId() %> - now;
            var distanceEnd = endDate_<%= pojo.getProductId() %> - now;

            var days = Math.floor(distanceStart / (1000 * 60 * 60 * 24));
            var hours = Math.floor((distanceStart % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            var minutes = Math.floor((distanceStart % (1000 * 60 * 60)) / (1000 * 60));
            var seconds = Math.floor((distanceStart % (1000 * 60)) / 1000);

            if (distanceStart > 0) {
              countdownElement_<%= pojo.getProductId() %>.innerHTML = "Starts in: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
            } else if (distanceEnd > 0) {
              days = Math.floor(distanceEnd / (1000 * 60 * 60 * 24));
              hours = Math.floor((distanceEnd % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
              minutes = Math.floor((distanceEnd % (1000 * 60 * 60)) / (1000 * 60));
              seconds = Math.floor((distanceEnd % (1000 * 60)) / 1000);
              countdownElement_<%= pojo.getProductId() %>.innerHTML = "Ends in: " + days + "d " + hours + "h " + minutes + "m " + seconds + "s ";
            } else {
              clearInterval(countdownInterval_<%= pojo.getProductId() %>);
              countdownElement_<%= pojo.getProductId() %>.innerHTML = "Auction ended";
            }
          }

          countdownInterval_<%= pojo.getProductId() %> = setInterval(updateCountdown_<%= pojo.getProductId() %>, 1000);
          updateCountdown_<%= pojo.getProductId() %>();
        </script>
        <%                
                } 
            } else { 
        %>
        <p>No properties have been approved yet.</p>
        
        <% 
            } 
        %>
    </div>
  </section>
  <footer>
    <p>&copy; 2024 Auction Website</p>
  </footer>
  
  <script>
    function toggleBidForm(productId) {
        var bidForm = document.getElementById("bidForm_" + productId);
        if (bidForm.style.display === "none") {
            bidForm.style.display = "block";
        } else {
            bidForm.style.display = "none";
        }
    }

    function submitBid(productId, startAmount, maxAmount) {
        var bidAmount = parseFloat(document.getElementById("bidAmount_" + productId).value);
        if (isNaN(bidAmount) || bidAmount < startAmount || bidAmount > maxAmount) {
            alert("Please enter a valid bid amount between " + startAmount + " and " + maxAmount + ".");
            return;
        }
        
        alert("Bid submitted successfully: " + bidAmount);
    }
  </script>
</body>
</html>

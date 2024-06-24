<%@ page import="com.auction.model.AuctionPojo" %>
<%@ page import="com.auction.util.JdbcAuction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
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
      font-size:8px;
      padding: 10px 0;
      text-align: center;
      position: fixed;
      top: 0;
      width: 100%;
      z-index: 1000;
    }
    nav {
      background-color: #f4f4f4;
      padding: 5px 10px;
    }
    nav ul {
      list-style-type: none;
      margin: 0;
      padding: 0;
      text-align: center;
    }
    nav ul li {
      display: inline-block;
      margin-right: 20px;
    }
    nav ul li a {
      color: black;
      text-decoration: none;
      font-size:15px;
      padding: 10px 15px;
      display: inline-block;
    }
    section {
      padding-top: 100px; 
      padding-left: 20px;
    }
    .card-container {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
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
      height: auto;
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
      padding: 10px 0;
      text-align: center;
      position: fixed;
      bottom: 0;
      width: 100%;
    }
    .card button {
      width: 100%;
      padding: 8px;
      margin-top: 10px;
    }
    .bid-form {
      margin-top: 10px;
    }
    .bid-form label {
      display: block;
      margin-bottom: 10px;
    }
    .bid-form input[type="number"] {
      width: calc(100% - 16px);
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
    .no-products-found {
      display: none;
      text-align: center;
      margin-top: 20px;
      font-size: 18px;
      color: red;
    }
  </style>
</head>
<body>
  <header>
    <h1>Welcome Bidders!</h1>
    <nav>
      <ul>
        <li><a href="login.jsp">Logout</a></li>
        <li>
          <form id="searchForm" action="SearchServlet" method="get">
            <input id="searchInput" type="text" name="search" placeholder="Search Product Category...">
            <button type="submit">Search</button>
          </form>
        </li>
      </ul>
    </nav>
  </header>
  <section id="view-users">
    <h2>Approved Products</h2>
    <div class="card-container">
    <%-- <% String bidStatus=null; %> --%>
      <%         
        JdbcAuction jdbcAuction = new JdbcAuction();
        ArrayList<AuctionPojo> approvedProducts = jdbcAuction.getApprovedProduct();
        if (approvedProducts != null && !approvedProducts.isEmpty()) {
          for (AuctionPojo pojo : approvedProducts) {
            String base64Image = "";
            if (pojo.getImage() != null && pojo.getImage().length > 0) {
              base64Image = Base64.getEncoder().encodeToString(pojo.getImage());
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currentDate = new Date();
            String currentDateString = sdf.format(currentDate);
            String startDateString = sdf.format(pojo.getStartDate());
            String endDateString = sdf.format(pojo.getEndDate());
      %>
      <div class="card" id="card_<%= pojo.getProductId() %>">
        <img class="product-image" src="data:image/jpeg;base64,<%=base64Image%>" alt="">
        <p>Seller Name: <%= pojo.getName() %></p>
        <p>Product Name: <%= pojo.getProductName() %></p>
        <p>Product Model: <%= pojo.getProductModel() %></p>
        <p>Product Category: <%= pojo.getProductCategory() %></p>
        <p>Product Condition: <%= pojo.getProductCondition() %></p>
        <p>Product Description: <%= pojo.getProductDescription() %></p>
        <p>Terms and Condition: <%= pojo.getTerms() %></p>
        <p>Starting Bid Amount: <%= pojo.getAverageAmount() %></p>
        <p>Bid Start Date: <%= pojo.getStartDate() %></p>
        <p>Bid End Date: <%= pojo.getEndDate() %></p>
        <div class="countdown" id="countdown_<%= pojo.getProductId() %>"></div>
        <% 
        AuctionPojo user=(AuctionPojo)session.getAttribute("userid") ;
        AuctionPojo user1=(AuctionPojo)session.getAttribute("username") ;
          if (endDateString.compareTo(currentDateString) > 0) { 
        %>
      <%--  <% bidStatus= "true"; %> --%>
          <button type="button" onclick="toggleBidForm('<%= pojo.getProductName() %>')" title="bid">Bid Now</button>
          <form id="bidForm_<%= pojo.getProductName() %>" class="bid-form" style="display: none;" action="BidAuction" method="post">
            <input type="hidden" name="userId" value="<%=user.getId() %>">
            <input type="hidden" name="biddername" value="<%= user1.getName() %>">
            <input type="hidden" name="productname" value="<%= pojo.getProductName() %>">
            <label for="bidAmount_<%= pojo.getProductName() %>">Enter Bid Amount:</label>
            <input type="number" id="bidAmount_<%= pojo.getProductName() %>" name="bidAmount" required value="<%= session.getAttribute("bidAmount") %>">
            <input type="number" id="bidAmount_<%= pojo.getProductName() %>" name="bidAmount" required>
            <button type="submit">Submit Bid</button>
          </form>
           <form action="ViewBidders" method="get">
          <input type="hidden" name="productname" value="<%= pojo.getProductName() %>">
          <%-- <input type="hidden" name="bidStatus" value="<%=  bidStatus%>"> --%>
          <button type="submit">View Bidders</button>
        </form>
        <% } else { %>
          <p>Auction ended</p>
            <%-- <% bidStatus= "false"; %> --%>
             <form action="Winners" method="get">
          <input type="hidden" name="productname" value="<%= pojo.getProductName() %>">
         <%--  <input type="hidden" name="bidStatus" value="<%=  bidStatus%>"> --%>
          <button type="submit">View Winners</button>
        </form>
        <% } %>
        
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

          var days, hours, minutes, seconds;

          if (distanceStart > 0) {
            days = Math.floor(distanceStart / (1000 * 60 * 60 * 24));
            hours = Math.floor((distanceStart % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
            minutes = Math.floor((distanceStart % (1000 * 60 * 60)) / (1000 * 60));
            seconds = Math.floor((distanceStart % (1000 * 60)) / 1000);
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
            var bidForm = document.getElementById("bidForm_<%= pojo.getProductName() %>");
            if (bidForm) {
              bidForm.style.display = "none"; 
            }
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
    <p id="noProductsFoundMessage" class="no-products-found">No products found.</p>
  </section>
  
  <script>
    document.addEventListener('DOMContentLoaded', function () {
      const searchInput = document.getElementById('searchInput');
      const noProductsFoundMessage = document.getElementById('noProductsFoundMessage');

      searchInput.addEventListener('input', function () {
        const searchValue = searchInput.value.toLowerCase();
        const cards = document.querySelectorAll('.card');
        let visibleCardCount = 0;

        cards.forEach(card => {
          const productCategory = card.querySelector('p:nth-of-type(4)').textContent.toLowerCase();
          if (productCategory.includes(searchValue)) {
            card.style.display = '';
            visibleCardCount++;
          } else {
            card.style.display = 'none';
          }
        });

        if (visibleCardCount === 0) {
          noProductsFoundMessage.style.display = 'block';
        } else {
          noProductsFoundMessage.style.display = 'none';
        }
      });
    });
    
    function toggleBidForm(productCategory) {
      var bidForm = document.getElementById("bidForm_" + productCategory);
      if (bidForm.style.display === "none" || bidForm.style.display === "") {
        bidForm.style.display = "block";
      } else {
        bidForm.style.display = "none";
      }
    }
  </script>
</body>
</html>

<%@ page import="com.auction.model.AuctionPojo"%>
<%@ page import="com.auction.util.JdbcAuction"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Base64"%>
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
    nav ul li a {
      color: black;
      text-decoration: none;
      font-size: 16px;
    }
    section {
      padding: 20px;
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

		<nav>
			<ul>
				<li><a href="homepage.jsp">Home</a></li>
				<form id="searchForm" action="SearchServlet" method="get">
        <input id="searchInput" type="text" name="search" placeholder="Search Product Category...">
        <button type="submit">Search</button>
      </form>
				
			</ul>
		</nav>
	</header>
	<section id="view-users">
		<h2>Approved User Products</h2>
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
				<img class="product-image"
					src="data:image/jpeg;base64,<%=base64Image%>" alt="">
				
				<p>
					User Name:
					<%= pojo.getName() %></p>
				
				<p>
					Product Name:
					<%= pojo.getProductName() %></p>
				<p>
					Product Model:
					<%= pojo.getProductModel() %></p>
				<p>
					Product Category:
					<%= pojo.getProductCategory() %></p>
				<p>
					Product Condition:
					<%= pojo.getProductCondition() %></p>
				<p>
					Product Description:
					<%= pojo.getProductDescription() %></p>
				<p>
					Terms and Condition :
					<%= pojo.getTerms() %></p>
				<p>
					Starting Bid Amount :
					<%= pojo.getAverageAmount() %></p>
			
				<p>
					Bid Start Date:
					<%= pojo.getStartDate() %></p>
				<p>
					Bid End Date:
					<%= pojo.getEndDate() %></p>

				<% 
    AuctionPojo userId=(AuctionPojo)session.getAttribute("userid"); 
    
    %>
    <% 
    AuctionPojo userName=(AuctionPojo)session.getAttribute("username"); 
    
    %>


				<form action="UserViewBidders" method="get">
					<input type="hidden" name="productname"
						value="<%= pojo.getProductName() %>">
					<button type="submit">View Bidders</button>
				</form>
			</div>
		
		<%
                }
            }
		%>
		</div>
	</section>
	<footer>
		<p>&copy; 2024 Auction Website</p>
	</footer>
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

<%@ page import="com.auction.model.AuctionPojo" %>
<%@ page import="com.auction.util.JdbcAuction" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %> 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard - Bidderyboy Auction Website</title>
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
      position:fixed;
      bottom:0;
      width:100%;
    }
  </style>
</head>
<body>
  <header>
    <h1>Admin Dashboard</h1>
    <nav>
      <ul>
        <li><a href="homepage.jsp">Home</a></li>
        <li><a href="Admin">Approved Products</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="about.jsp">About</a></li>
      </ul>
    </nav>
  </header>
  <section id="view-users">
    <table border="1">
      <thead>
        <th>User ID</th>
        <th>User Name</th>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Product Image</th>
        <th>Product Model</th>
        <th>Product Category</th>
        <th>Product Condition</th>
        <th>Product Description</th>
        <th>Terms and Conditions</th>
        <th>Average Amount</th>
        <th>Maximum Amount</th>
        <th>Bid Start Date</th>
        <th>Bid End Date</th>
        <th>ISO Certificate</th>
        <th>Action</th>
        <th>Approval</th>
      </thead>
      <tbody>
        <% 
        JdbcAuction user=new JdbcAuction();
        ArrayList<AuctionPojo> array = user.selectAllUsers();
        for (AuctionPojo pojo : array) {
          String base64Image = "";
          String base64Image1 = "";
          if (pojo.getImage() != null && pojo.getIso() != null) {
            base64Image = Base64.getEncoder().encodeToString(pojo.getImage());
            base64Image1 = Base64.getEncoder().encodeToString(pojo.getIso());
          }
        %>
        <tr>
          <td><%=pojo.getId() %></td>
          <td><%=pojo.getName() %></td>
          <td><%=pojo.getProductId() %></td>
          <td><%=pojo.getProductName() %></td>
          <td><img class="product-image" src="data:image/jpeg;base64,<%=base64Image%>" alt="Image"></td>
          <td><%= pojo.getProductModel() %></td>
          <td><%= pojo.getProductCategory() %></td>
          <td><%= pojo.getProductCondition() %></td>
          <td><%= pojo.getProductDescription() %></td> 
          <td><%= pojo.getTerms() %></td>  
          <td><%= pojo.getAverageAmount() %></td>  
          <td><%= pojo.getMaximumAmount() %></td>  
          <td><%= pojo.getStartDate() %></td> 
          <td><%= pojo.getEndDate() %></td> 
          <td><img class="product-image" src="data:image/jpeg;base64,<%=base64Image1%>" alt="Image"></td> 
          <td>
            <form action="AuctionOnline" method="post">
              <input type="hidden" name="action" value="delete">
              <input type="hidden" name="deleteproductid" value="<%= pojo.getProductId() %>">
              <button type="submit" title="delete">Delete</button>
            </form>
          </td>
          <td>
            <form action="Admin" method="post">
              <input type="hidden" name="approveid" value="<%= pojo.getId() %>">
              <button type="submit" title="approve">Approve</button>
            </form>
          </td>
        </tr>
        <% 
        }
        %>
      </tbody>
    </table>
  </section>
  <footer>
    <p>&copy; 2024 Auction Website. All rights reserved</p>
  </footer>
</body>
</html>
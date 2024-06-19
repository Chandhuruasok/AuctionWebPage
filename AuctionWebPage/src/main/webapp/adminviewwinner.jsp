<%@ page import="com.auction.model.ViewAmountPojo" %>
<%@ page import="java.util.ArrayList" %>

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
  </style>
</head>
<body>
  <header>
    <h1>Winners List!</h1>
    <nav>
      <ul>
        <li><a href="homepage.jsp">Home</a></li>
        <li><a href="login.jsp">Logout</a></li>
        </ul>
    </nav>
  </header>
  <section id="view-users">
    <h2>Winners List</h2>
    <table border="1">
      <thead>
        <tr>
          
          <th>Bidder Name</th>
          <th>Product Name</th>
          <th>Bid Amount</th>
        </tr>
      </thead>
      <tbody>
        <%
          ArrayList<ViewAmountPojo> approvedIds = (ArrayList<ViewAmountPojo>) request.getAttribute("approvedIds");
          if (approvedIds != null && !approvedIds.isEmpty()) {
            for (ViewAmountPojo viewPojo : approvedIds) {
        %>
        <tr>
          
          <td><%= viewPojo.getBidderName() %></td>
          <td><%= viewPojo.getProductName() %></td>
          <td><%= viewPojo.getBidAmount() %></td>
        </tr>
        <%
            }
          }
        %>
      </tbody>
    </table>
  </section>
  <footer>
    <p>&copy; 2024 Auction Website</p>
  </footer>
</body>
</html>

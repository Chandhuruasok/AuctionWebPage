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
      background-color: #f0f0f0;
      display: flex;
      flex-direction: column;
    }
    header {
      background-color:green;
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
      background-color: green;
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
    <h1>Bidder Dashboard</h1>
    <nav>
      <ul>
        <li><a href="homepage.jsp">Home</a></li>
        <li><a href="Admin">Approved Products</a></li>
      </ul>
    </nav>
  </header>
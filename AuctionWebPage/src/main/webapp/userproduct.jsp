<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.auction.model.AuctionPojo" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bidderboy Auction Website</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      background-color: #f1f1f1;
    }
    .container {
      max-width: 600px;
      margin: auto;
      background-color: #fff;
      padding: 20px;
      border-radius: 5px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .form-group {
      margin-bottom: 20px;
    }
    .form-group label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }
    .form-group input,
    .form-group select,
    .form-group textarea {
      width: 100%;
      padding: 10px;
      border-radius: 7px;
      border: 3px solid #ccc;
      box-sizing: border-box;
      margin-top: 5px;
    }
    .form-group textarea {
      height: 100px;
    }
    .form-group button {
      background-color: blue;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .form-group button:hover {
      background-color: black;
    }
  </style>
</head>
<body>

<div class="container">
  <h2>Product Details</h2>
  <form action="AuctionOnline" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="name">User Name:</label>
      <input type="text" id="name" name="name" required>
    </div>
    
    <div class="form-group">
      <label for="product_name">Product Name:</label>
      <input type="text" id="product_name" name="product_name" required>
    </div>
    <div class="form-group">
      <label for="image">Image:</label>
      <input type="file" id="image" name="image" accept="image/*">
    </div>
    <div class="form-group">
      <label for="product_model">Product Model:</label>
      <input type="text" id="product_model" name="product_model">
    </div>
    <div class="form-group">
      <label for="product_category">Product Category:</label>
      <input type="text" id="product_category" name="product_category">
    </div>
    <div class="form-group">
      <label for="product_condition">Product Condition:</label>
      <select id="product_condition" name="product_condition">
        <option value="new">New</option>
        <option value="used">Used</option>
        <option value="refurbished">Refurbished</option>
      </select>
    </div>
    <div class="form-group">
      <label for="product_description">Product Description:</label>
      <textarea id="product_description" name="product_description" required></textarea>
    </div>
    <div class="form-group">
      <label for="terms_and_conditions">Terms and Conditions:</label>
      <textarea id="terms_and_conditions" name="terms_and_conditions" required></textarea>
    </div>
    <div class="form-group">
      <label for="average_amount">Bid Start Amount:</label>
      <input type="number" id="average_amount" name="average_amount" required>
    </div>
    <div class="form-group">
      <label for="maximum_amount">Maximum Amount:</label>
      <input type="number" id="maximum_amount" name="maximum_amount" required>
    </div>
    <div class="form-group">
      <label for="bid_start_date">Bid Start Date:</label>
      <input type="date" id="bid_start_date" name="bid_start_date" required>
    </div>
    <div class="form-group">
      <label for="bid_end_date">Bid End Date:</label>
      <input type="date" id="bid_end_date" name="bid_end_date" required>
    </div>
    <div class="form-group">
    <label for="myfile">ISO Certificate:</label>
      <input type="file" id="myfile" name="myfile" multiple">
    </div>
    <% 
    AuctionPojo userId=(AuctionPojo)session.getAttribute("userid"); 
    
    %>
    <input type="hidden" value="<%=userId.getId()%>" name="id">
    
    <div class="form-group">
      <button type="submit">Submit</button>
      <input type="hidden" name="action" value="user">
      <input type="hidden" name="user">
  </form>
</div>

</body>
</html>
    
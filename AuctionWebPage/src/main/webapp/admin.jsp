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
      background-color: #333;
      color: #fff;
      padding: 10px;
      text-align: center;
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
    footer {
      background-color: #333;
      color: #fff;
      padding: 10px;
      text-align: center;
      position: fixed;
      bottom: 0;
      width: 100%;
    }
  </style>
</head>
<body>
  <header>
    <h1>Welcome Admin!</h1>
  </header>
  <nav>
    <ul>
      <li><a href="">View Users</a></li>
    </ul>
  </nav>
  <section id="view-users">
    <h2>User Details</h2>
    
		<table border="1">      
        <tr>
          <th>User ID</th>
          <th>User Name</th>
          <th>Product Id</th>
          <th>Product Image</th>
          <th>Product Model</th>
          <th>Product Category</th>
          <th>Product Description</th>
          <th>Terms and Conditions</th>
          <th>Average Amount</th>
          <th>Maximum Amount</th>
          <th>Bid Start Date</th>
          <th>Bid End Date</th>
        </tr>
      
      
        
        <tbody>
     
        <% 
        JdbcAuction user=new JdbcAuction();
        ArrayList<AuctionPojo> array = user.selectAllUsers();
      
           for (AuctionPojo pojo : array) {
        	   String base64Image = Base64.getEncoder().encodeToString(pojo.getImage());
        %>
        <tr>
            
            <td><%=pojo.getId() %></td>
            <td><%=pojo.getName() %></td>
            <td><%=pojo.getProductId() %></td>
            <td><img src="data:image/jpeg;base64,<%=base64Image%>" alt="Image"></td>
            <td><%= pojo.getProductModel() %></td>
             <td><%= pojo.getProductDescription() %></td>
             <td><%= pojo.getProductCategory() %></td>
             <td><%= pojo.getProductDescription() %></td>  
             <td><%= pojo.getProductCondition() %></td>   
             <td><%= pojo.getTerms() %></td>  
             <td><%= pojo.getAverageAmount() %></td>  
             <td><%= pojo.getMaximumAmount() %></td>  
             <td><%= pojo.getStartDate() %></td> 
             <td><%= pojo.getEndDate() %></td>  
        </tr>
        <% 
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

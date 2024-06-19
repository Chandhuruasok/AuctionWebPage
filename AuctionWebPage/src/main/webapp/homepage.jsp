<!DOCTYPE html>
<html lang="${langAttribute}">
<head>
<meta charset="ISO-8859-1">
<title>Bidderboy Auction Website</title>
<style>
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    
}

nav {
    background-color:green; 
    overflow: hidden;
}

nav ul {
    list-style-type: none;
    margin: 10px;
    padding: 0;
    display: flex;
}

nav ul li {
    flex: 1;
}

nav ul li a {
    display: block;
    color: white;
    text-align: center;
    padding-top: 40px;
    text-decoration: none;
}

nav ul li a:hover {
    background-color: #111;
}

#content {
    padding: 20px;
}

h1, h2 {
    color: #333;
}

p, ul, ol {
    line-height: 1.6;
}

ul {
    list-style-type: disc;
    margin-left: 20px;
}

ol {
    list-style-type: decimal;
    margin-left: 20px;
}
nav ul li img {
    height: 100px;
    width: auto; 
    margin-right: 30px; 
}
.foot
{
background-color:green;
position:fixed;
bottom:0;

width:100%;
}
.foot p
{
padding-bottom:1px;
text-align:center;
color:white;
}
</style>
</head>
<body>
<nav>
    <ul>

       <li><a href="homepage.jsp">Home</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="about.jsp">About</a></li>
    </ul>
</nav>
<div id="content">
	
      <h1>Welcome to Bidderboy</h1>
    
    <p>Welcome to Bidderboy, the premier online auction platform. We bring buyers and sellers together from around the globe, providing a secure and reliable marketplace for trading a wide variety of items.</p>
    <h2>How It Works</h2>
    <p>Our platform is designed to be easy to use for both buyers and sellers:</p>
    <ol>
        <li><strong>Sign Up:</strong> Create an account to start bidding or listing items.</li>
        <li><strong>List Items:</strong> Sellers can list items with detailed descriptions and photos.</li>
        <li><strong>Bid or Buy:</strong> Buyers can bid on items or choose the 'Bid Now' option.</li>
        <li><strong>Secure Payment:</strong> All transactions are processed securely through our platform.</li>
        <li><strong>Delivery:</strong> Coordinate shipping or pick-up directly with the seller.</li>
    </ol>
</div>
<div class="foot">
<footer>
<p>&copy;2024 e-Auction.All rights reserved.</p>
</footer>
</div>
</body>
</html>
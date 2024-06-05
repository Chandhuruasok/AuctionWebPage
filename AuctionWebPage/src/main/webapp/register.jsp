<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bidderboy Auction Website</title>
<style>
* {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
}
nav {
    background-color: #28a745; 
    overflow: hidden;
    top:0;
    width:100%;
    position:fixed;
}

nav ul {
    list-style-type: none;
    display: flex;
}

nav ul li {
    flex: 1;
}

nav ul li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

nav ul li a:hover {
    background-color: #111;
}

body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f4f4f4;
    padding-top:50px;
    
}

.container {
    background-color: #fff;
    padding: 20px;
    width: 300px;
}

.registration-form h2 {
    text-align: center;
    margin-bottom: 20px;
}

.form-group {
    margin-bottom: 10px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

.form-group input {
    width: 100%;
    padding: 10px;
    border-radius: 1px solid #ccc;
}

button {
    width: 100%;
    padding: 10px;
    border: none;
    
    background-color: #5cb85c;
    color: white;
    font-size: 18px;
    cursor: pointer;
}
button:hover {
    background-color: black;
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
padding-bottom:10px;
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
<div class="container">
        <form action="AuctionOnline" method="post" class="registration-form">
            <h2>Registration Form</h2>

            <div class="form-group">
           		<label for="email">Email</label>
                <input type="email" id="email" name="email" pattern="^(?=.*[a-z])+@(?=.*[a-z])+.(?=.*[a-z]){6,20}$" required>
            </div>
            <div class="form-group">
            	<label for="name">User Name</label>
                <input type="text" id="name" name="name" pattern="[A-Za-z]{4,}" required>
            </div>
            <div class="form-group">
                <label for="phonenumber">Phone Number</label>
                <input type="tel" id="phonenumber" name="phonenumber" pattern="[0-9]{10}" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#&]).{5,}" required>
            </div>
            <div class="form-group">
                <label for="confirmpassword">Confirm Password</label>
                <input type="password" id="confirmpassword" name="confirmpassword" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#&]).{5,}" required>
            </div>
            
            <button type="submit">Register</button>
            <input type="hidden" name="action" value="register">
            <input type="hidden" name="register" >
        </form>
    </div>
    <div class="foot">
<footer>
<p>&copy;2024 e-Auction.All rights reserved.</p>
</footer>
</div>
</body>
</html>
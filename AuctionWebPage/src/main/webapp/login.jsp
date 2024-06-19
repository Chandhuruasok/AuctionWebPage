<!DOCTYPE html>
<html lang="en">
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

body {
    background-image: url('bid1.jpg');
    background-position:6000px;
    background-size: 1000px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 110vh;
    
}

nav {
    background-color: green; 
    overflow: hidden;
    top: 0;
    width: 100%;
    position: fixed;
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
    padding-top: 40px;
    text-decoration: none;
}

nav ul li a:hover {
    background-color: #111;
}

nav ul li img {
    height: 100px;
    width: auto; 
    margin-right: 30px; 
}

.container {
    background-color: rgba(255, 255, 255, 0.8); /* Add background color with transparency */
    padding: 20px;
    width: 300px;
}

.login-form h2 {
    text-align: center;
    margin-bottom: 30px;
}

.form-group {
    margin-top: 20px;
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
    margin-top: 10px;
    background-color: #5cb85c;
    color: white;
    font-size: 18px;
    cursor: pointer;
}

button:hover {
    background-color: black;
}

.redirect-text {
    text-align: center;
    margin-top: 15px;
}

.redirect-text a {
    color: #28a745;
    text-decoration: none;
}

.redirect-text a:hover {
    text-decoration: underline;
}

.foot {
    background-color: green;
    position: fixed;
    bottom: 0;
    width: 100%;
}

.foot p {
    padding-bottom: 10px;
    text-align: center;
    color: white;
}
</style>
</head>
<body>
<nav>
    <ul>
    	 <li><img src="Images/bidimage" alt="logo"></li>
        <li><a href="homepage.jsp">Home</a></li>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="about.jsp">About</a></li>
    </ul>
</nav>
<div class="container">
    <form action="AuctionOnline" method="post" class="login-form">
       	<h2>Login Form</h2>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="name">User Name</label>
            <input type="text" id="username" name="name" pattern="[A-Za-z]{4,}" required>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#&]).{5,}" required>
        </div>
        
        <input type="hidden" name="action" value="login">
        <input type="hidden" name="login">
        <button type="submit">Login</button>
        <div class="redirect-text">
            <p>Don't have an account?<a href="register.jsp">Register</a></p>
        </div>
    </form>
</div>
<div class="foot">
<footer>
<p>&copy;2024 e-Auction. All rights reserved.</p>
</footer>
</div>
</body>
</html>

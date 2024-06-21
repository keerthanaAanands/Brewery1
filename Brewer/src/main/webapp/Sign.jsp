<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Sign.css"></link>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>

<div class="wrapper">
<form action="sign" method="post">
<div class= "register-link">
<p> Do you already have an account?<a href="home.jsp">Login</a></p>
</div>
<h1>Sign up</h1>

<div class="input-box">
<input type="text" name="uid" placeholder= "User Name">
<i class='bx bxs-user'></i>
</div>

<div class="input-box">
<input type= "text" name="uem" placeholder="Email Id">
<i class='bx bxs-envelope'></i>
</div>

<div class="input-box">
<input type="password" name="npass" placeholder="New Password">
<i class='bx bxs-lock-alt' ></i>
</div>

<div class="input-box">
<input type="password" name="conf" placeholder="Confirm Password">
<i class='bx bxs-lock-alt' ></i>
</div>

<button type="submit"  class="btn">Register</button>
</form>

</div>

</body>
</html>
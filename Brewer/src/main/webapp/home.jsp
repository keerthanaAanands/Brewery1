<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="home.css">
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>

<div class="wrapper">
<form action="home" method="post">
<h1>Login</h1>

<div class="input-box">
<input type="text" placeholder="User ID" name="un" required>
<i class='bx bxs-user'></i>
</div>

<div class="input-box">
<input type="password" placeholder="Password" name="up" required>
<i class='bx bxs-lock-alt'></i>
</div>

<button type="submit" class="btn">Login</button>

<div class="register-link">
<p>Don't have an account? <a href="Sign.jsp">SignUp</a></p>
</div>

</form>
</div>

</body>
</html>
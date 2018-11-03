<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/formStyle.css">
		<title>Login</title>
	</head>
	<body id="telaLogin" background="img/universidade.png">
    	<div class="loginbox">
    	<img src="img/avatar.png" class="avatar">
        	<h1>Login Here</h1>
        	<form action="efetuaLogin" method="post">
            	<p>Username</p>
            	<input type="text" name="login" placeholder="Enter Username">
            	<p>Password</p>
            	<input type="password" name="senha" placeholder="Enter Password">
            	<input type="submit" value="Login">
            	<a href="#">Lost your password?</a><br>
            	<a href="#">Don't have an account?</a>
        	</form>
    	</div>
	</body>
</html>
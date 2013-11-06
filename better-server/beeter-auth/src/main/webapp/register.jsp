<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrame</title>
</head>
<body>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/login.css">
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.1/css/bootstrap-theme.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
	
	<div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Identificate</h1>
            <div class="account-wall">
		<img class="profile-img" src="http://www.anaderwei.com/wp-content/uploads/2013/07/social-networking.jpg" alt="">
		<form class="form-signin" name="formularioREG" action="/beeter-auth/RegisterServlet" method="POST">
                <input type="text" name="name" class="form-control" placeholder="Name" required="" autofocus="">
                <input type="text" name="email" class="form-control" placeholder="E-Mail" required="" autofocus="">
                <input type="text" name="username" class="form-control" placeholder="Username" required="" autofocus="">
                <input type="password" name="userpass" class="form-control" placeholder="Password" required="">
                <input type="hidden" name="action" value="REGISTRO">
                <button class="btn btn-lg btn-primary btn-block" type="submit" value="Registrarme">Registrarme</button>
                <a href="#" class="pull-right need-help">Necesitas ayuda? </a>
                <span class="clearfix"></span>
                </form>
            </div>
        </div>
 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrate ahora!</title>
</head>
<body>
<font face="Times New Roman,Times" size="+3">Registro</font>
	<hr>
	<form name="formularioREG" action="/beeter-auth/RegisterServlet" method="POST">
		Username: <input type="text" name="username" value="McD0n3ld"><br>
		Pass:<input type="text" name="userpass" value="12345678"><br>
		Name:<input type="text" name="name" value="Raul"><br>
		Email:<input type="text" name="email" value="raul@dsa.crack"><br>
		<input type="hidden" name="action" value="REGISTRO"> 
		<input type="submit" name="Submit" value="Registrar">
	</form>
</body>
</html>
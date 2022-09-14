<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Curso JSP</title>
</head>
<body>

	<h1>Bem vindo!</h1>

	<form action="ServletLogin" method="post">
	<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
		<table>
			<tr>
				<td> <label>Email</label> </td>
				<td><input name="Email" type="text"></td>
			</tr>
			<tr>
				<td> <label>Senha</label> </td>
				<td><input name="Senha" type="password"></td>
			</tr>
			<tr>
				<td> <label></label> </td>
				<td><input type="submit" value="Enviar"></td>
			</tr>
		</table>
	</form>
	<h5>${msg}</h5>

</body>
</html>
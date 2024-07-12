<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="br.com.locacao.persistencia.entidade.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

Bem Vindo!
<% 
Usuario usuario = (Usuario)request.getSession().getAttribute("usuAutenticado");
out.print(usuario.getNome());
%>
<a href="logoutUsu">Sair</a>
</body>
</html>
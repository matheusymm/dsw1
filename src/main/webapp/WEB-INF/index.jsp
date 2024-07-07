<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="br.com.locacao.persistencia.entidade.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@include file="menu.jsp"%>

Bem Vindo!
<% 
Usuario usuario = (Usuario)request.getSession().getAttribute("usuAutenticado");
out.print(usuario.getNome());
%>
</body>
</html>
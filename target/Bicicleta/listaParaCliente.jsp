<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Locadora Virtual</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  </head>
  <body>
    <div align="center">
      <h1>Gerenciamento de Locadoras</h1>
      <h2>
        <a href="/Bicicleta">Menu Principal</a>
      </h2>
    </div>
    <div align="center">
      <h2>Lista de Locadoras</h2>
      <form action="${pageContext.request.contextPath}/locadoras" method="get">
        <label for="cidade">Cidade:</label>
        <input type="text" id="cidade" name="cidade">
        <button type="submit">Buscar</button>
      </form>
      <table border="1">
        <tr>
          <th>Nome</th>   
          <th>E-mail</th>
          <th>CNPJ</th>
          <th>Cidade</th>
        </tr>
        <c:forEach var="locadora" items="${requestScope.listaLocadoras}">
          <tr>
            <td>${locadora.nome}</td>
            <td>${locadora.email}</td>
            <td>${locadora.cnpj}</td>
            <td>${locadora.cidade}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>

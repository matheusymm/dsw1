<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Cliente Virtual</title>
  </head>
  <body>
    <div align="center">
      <h1>Gerenciamento de Locações</h1>
      <h2>
        <a href="/${requestScope.contextPath}/clientes">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/clientes/insercao">Nova Locação</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logout">Logout</a>
      </h2>
    </div>
    <div align="center">
      <h2>Lista de Locações</h2>
      <table border="1">
        <tr>
          <th>ID</th>
          <th>CPF Cliente</th>
          <th>CNPJ Locadora</th>
          <th>Horário</th>
        </tr>
        <c:forEach var="locacao" items="${requestScope.listaLocacoes}">
          <tr>
            <td>${locacao.id}</td>
            <td>${locacao.cpfCliente}</td>
            <td>${locacao.cnpjLocadora}</td>
            <td>${locacao.dataLocacao}</td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>

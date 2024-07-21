<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Cliente Virtual</title>
  </head>
  <body>
    <div align="center">
      <h1>Gerenciamento de Clientes</h1>
      <h2>Bem vindo, ${cliente.nome}!</h2>
      <h2>
        <a href="/${requestScope.contextPath}/clientes">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/clientes/cadastro">Nova Locação</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logout">Logout</a>
      </h2>
    </div>

    <div align="center">
        <h2>Lista de Locações</h2>
        <c:if test="${empty requestScope.listaLocacoes}">
          <h3>Não há locações cadastradas.</h3>
        </c:if>
        <c:if test="${not empty requestScope.listaLocacoes}">
          <table border="1">
            <tr>
              <th>CNPJ Locadora</th>
              <th>Horário</th>
            </tr>
            <c:forEach var="locacao" items="${requestScope.listaLocacoes}">
            <tr>
              <td>${locacao.cnpjLocadora}</td>
              <td>${locacao.dataLocacao}</td>
            </tr>
            </c:forEach>
          </table>
        </c:if>
    </div>
  </body>
</html>

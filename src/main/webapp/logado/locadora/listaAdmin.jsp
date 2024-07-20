<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Locadora Virtual</title>
  </head>
  <body>
    <div align="center">
      <h1>Gerenciamento de locadoras</h1>
      <h2>Bem vindo, ${locadora.nome}!</h2>
      <h2>
        <a href="/${requestScope.contextPath}/adminsLocadora">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/adminsLocadora/cadastro">Adicione Novo locadora</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logout">Logout</a>
      </h2>
    </div>

    <div align="center">
      <h2>Lista de Locadoras</h2>
      <c:if test="${empty requestScope.listaLocadoras}">
          <h3>Não há locadoras cadastradas.</h3>
      </c:if>
      <c:if test="${not empty requestScope.listaLocadoras}">
        <table border="1">
          <tr>
            <th>ID</th>
            <th>E-mail</th>
            <th>Senha</th>
            <th>CNPJ</th>
            <th>Nome</th>
            <th>Cidade</th>
            <th>Papel</th>
            <th>Ação</th>
          </tr>
          <c:forEach var="locadora" items="${requestScope.listaLocadoras}">
            <tr>
              <td>${locadora.id}</td>
              <td>${locadora.email}</td>
              <td>${locadora.senha}</td>
              <td>${locadora.cnpj}</td>
              <td>${locadora.nome}</td>
              <td>${locadora.cidade}</td>
              <td>${locadora.papel}</td>
              <td>
                <a href="/${requestScope.contextPath}/adminsLocadora/edicao?id=${locadora.id}">Edição</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/${requestScope.contextPath}/adminsLocadora/remocao?id=${locadora.id}"onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                  Remoção
                </a>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
    </div>
  </body>
</html>

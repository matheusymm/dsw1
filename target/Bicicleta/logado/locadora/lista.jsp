<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Locadora Virtual</title>
  </head>
  <body>
    <div align="center">
      <h1>Gerenciamento de Locadoras</h1>
      <h2>
        <a href="/${requestScope.contextPath}">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/locadoras/cadastro">Adicione Nova Locadora</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/">Logout</a>
      </h2>
    </div>

    <div align="center">
      <table border="1">
        <caption>
          Lista de Locadoras
        </caption>
        <tr>
          <th>ID</th>
          <th>E-mail</th>
          <th>Senha</th>
          <th>CNPJ</th>
          <th>Nome</th>
          <th>Cidade</th>
          <th>Papel</th>
          <th>Acao</th>
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
              <a
                href="/${requestScope.contextPath}/locadoras/edicao?id=${locadora.id}">Edição</a>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <a href="/${requestScope.contextPath}/locadoras/remocao?id=${locadora.id}" onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                Remoção
              </a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>

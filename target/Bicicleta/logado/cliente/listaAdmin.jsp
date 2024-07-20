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
        <a href="/${requestScope.contextPath}/adminsCliente">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/adminsCliente/cadastro">Adicione Novo Cliente</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logout">Logout</a>
      </h2>
    </div>

    <div align="center">
      <h2>Lista de Clientes</h2>
      <c:if test="${empty requestScope.listaClientes}">
          <h3>Não há clientes cadastrados.</h3>
      </c:if>
      <c:if test="${not empty requestScope.listaClientes}">
        <table border="1">
          <tr>
            <th>ID</th>
            <th>E-mail</th>
            <th>Senha</th>
            <th>Nome</th>
            <th>CPF</th>
            <th>Telefone</th>
            <th>Sexo</th>
            <th>Data de Nascimento</th>
            <th>Papel</th>
            <th>Acao</th>
          </tr>
          <c:forEach var="cliente" items="${requestScope.listaClientes}">
            <tr>
              <td>${cliente.id}</td>
              <td>${cliente.email}</td>
              <td>${cliente.senha}</td>
              <td>${cliente.nome}</td>
              <td>${cliente.cpf}</td>
              <td>${cliente.telefone}</td>
              <td>${cliente.sexo}</td>
              <td>${cliente.dataNascimento}</td>
              <td>${cliente.papel}</td>
              <td>
                <a href="/${requestScope.contextPath}/admins/edicao?id=${cliente.id}">Edição</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/${requestScope.contextPath}/admins/remocao?id=${cliente.id}"onclick="return confirm('Tem certeza de que deseja excluir este item?');">
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

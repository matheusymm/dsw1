<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    Sistema de Locacao de Bicicletas
  </head>
  <body>
    <div align="center">
      <h1>Gerenciamento de Clientes</h1>
      <h2>
        <a href="/${requestScope.contextPath}">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/clientes/cadastro"
          >Cadastrar Cliente</a
        >
      </h2>
    </div>
    <div align="center">
      <table border="1">
        <caption>
          Lista de Clientes
        </caption>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>CPF</th>
          <th>Telefone</th>
          <th>Email</th>
          <th>Data de Nascimento</th>
          <th>Editar</th>
          <th>Excluir</th>
        </tr>
        <c:forEach items="${requestScope.clientes}" var="cliente">
          <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.cpf}</td>
            <td>${cliente.telefone}</td>
            <td>${cliente.email}</td>
            <td>${cliente.data_nascimento}</td>
            <td>
              <a
                href="/${requestScope.contextPath}/clientes/editar/${cliente.id}"
                >Editar</a
              >
            </td>
            <td>
              <a
                href="/${requestScope.contextPath}/clientes/excluir/${cliente.id}"
                >Excluir</a
              >
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>

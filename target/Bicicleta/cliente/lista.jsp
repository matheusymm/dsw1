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
      <h2>
        <a href="/${requestScope.contextPath}">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/clientes/cadastro"
          >Adicione Novo Cliente</a
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
          <th>E-mail</th>
          <th>Senha</th>
          <th>Nome</th>
          <th>CPF</th>
          <th>Telefone</th>
          <th>Sexo</th>
          <th>Data de Nascimento</th>
          <th>Tipo</th>
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
            <td>${cliente.tipo}</td>
            <td>
              <a
                href="/${requestScope.contextPath}/clientes/edicao?id=${cliente.id}"
                >Edição</a
              >
              &nbsp;&nbsp;&nbsp;&nbsp;
              <a
                href="/${requestScope.contextPath}/clientes/remocao?id=${cliente.id}"
                onclick="return confirm('Tem certeza de que deseja excluir este item?');"
              >
                Remoção
              </a>
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>
  </body>
</html>

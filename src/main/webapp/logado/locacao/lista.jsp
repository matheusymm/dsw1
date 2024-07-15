<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Cliente Virtual</title>
  </head>
  <body>
    <div align="center">
      <h1>Gerenciamento de Locacoes</h1>
      <h2>
        <a href="/${requestScope.contextPath}">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/clientes/cadastro"
          >Adicione Nova Locacao</a
        >
      </h2>
    </div>

    <div align="center">
      <table border="1">
        <caption>
          Lista de Locacoes
        </caption>
        <tr>
          <th>ID</th>
          <th>CPF Cliente</th>
          <th>CNPJ Locadora</th>
          <!-- <th>Data/Hora</th> -->
          <th>Acao</th>
        </tr>
        <c:forEach var="locacao" items="${requestScope.listaLocacoes}">
          <tr>
            <td>${locacao.id}</td>
            <td>${locacao.cpfCliente}</td>
            <td>${locacao.cnpjLocadora}</td>
            <td>
              <a
                href="/${requestScope.contextPath}/locacaos/edicao?id=${locacao.id}"
                >Edição</a
              >
              &nbsp;&nbsp;&nbsp;&nbsp;
              <a
                href="/${requestScope.contextPath}/locacaos/remocao?id=${locacao.id}"
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

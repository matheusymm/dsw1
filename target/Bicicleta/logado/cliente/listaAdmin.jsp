<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<html>
  <head>
    <title><fmt:message key="cliente.titulo"/></title>
  </head>
  <body>
    <div align="center">
      <h1><fmt:message key="cliente.gerencia"/></h1>
      <h2><fmt:message key="locadora.ola"/></h2>
      <h2>
        <a href="/${requestScope.contextPath}/adminsCliente"><fmt:message key="menu"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/adminsCliente/cadastro"><fmt:message key="cliente.novo"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logout"><fmt:message key="sair"/></a>
      </h2>
    </div>

    <div align="center">
      <h2><fmt:message key="cliente.lista"/></h2>
      <c:if test="${empty requestScope.listaClientes}">
          <h3><fmt:message key="cliente.vazio"/></h3>
      </c:if>
      <c:if test="${not empty requestScope.listaClientes}">
        <table border="1">
          <tr>
            <th><fmt:message key="cliente.id"/></th>
            <th><fmt:message key="cliente.email"/></th>
            <th><fmt:message key="cliente.senha"/></th>
            <th><fmt:message key="cliente.nome"/></th>
            <th><fmt:message key="cliente.cpf"/></th>
            <th><fmt:message key="cliente.telefone"/></th>
            <th><fmt:message key="cliente.sexo"/></th>
            <th><fmt:message key="cliente.dataNascimento"/></th>
            <th><fmt:message key="cliente.papel"/></th>
            <th><fmt:message key="acao"/></th>
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
                <a href="/${requestScope.contextPath}/adminsCliente/edicao?id=${cliente.id}"><fmt:message key="editar"/></a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/${requestScope.contextPath}/adminsCliente/remocao?id=${cliente.id}"onclick="return confirm(<fmt:message key="remover.confirma"/>);">
                  <fmt:message key="remover"/>
                </a>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
    </div>
  </body>
</html>

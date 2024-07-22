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
        <a href="/${requestScope.contextPath}/clientes"><fmt:message key="menu"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/clientes/cadastro"><fmt:message key="locacao.novo"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logout"><fmt:message key="sair"/></a>
      </h2>
    </div>

    <div align="center">
        <h2><fmt:message key="locacao.lista"/></h2>
        <c:if test="${empty requestScope.listaLocacoes}">
          <h3><fmt:message key="locacao.vazio"/></h3>
        </c:if>
        <c:if test="${not empty requestScope.listaLocacoes}">
          <table border="1">
            <tr>
              <th><fmt:message key="locacao.cnpjLocadora"/></th>
              <th><fmt:message key="locacao.dataLocacao"/></th>
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

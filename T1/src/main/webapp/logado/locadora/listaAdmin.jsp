<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<html>
  <head>
    <title><fmt:message key="locadora.titulo"/></title>
  </head>
  <body>
    <div align="center">
      <h1><fmt:message key="locadora.gerencia"/></h1>
      <h2><fmt:message key="locadora.ola"/></h2>
      <h2>
        <a href="/${requestScope.contextPath}/adminsLocadora"><fmt:message key="menu"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/adminsLocadora/cadastro"><fmt:message key="locadora.novo"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/logout"><fmt:message key="sair"/></a>
      </h2>
    </div>

    <div align="center">
      <h2><fmt:message key="locadora.lista"/></h2>
      <c:if test="${empty requestScope.listaLocadoras}">
          <h3><fmt:message key="locadora.vazio"/></h3>
      </c:if>
      <c:if test="${not empty requestScope.listaLocadoras}">
        <table border="1">
          <tr>
            <th><fmt:message key="locadora.id"/></th>
            <th><fmt:message key="locadora.email"/></th>
            <th><fmt:message key="locadora.senha"/></th>
            <th><fmt:message key="locadora.cnpj"/></th>
            <th><fmt:message key="locadora.nome"/></th>
            <th><fmt:message key="locadora.cidade"/></th>
            <th><fmt:message key="locadora.papel"/></th>
            <th><fmt:message key="acao"/></th>
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
                <a href="/${requestScope.contextPath}/adminsLocadora/edicao?id=${locadora.id}"><fmt:message key="editar"/></a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/${requestScope.contextPath}/adminsLocadora/remocao?id=${locadora.id}"onclick="return confirm(<fmt:message key="remover.confirma"/>);">
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

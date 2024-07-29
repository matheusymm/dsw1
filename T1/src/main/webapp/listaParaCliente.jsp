<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<html>
  <head>
    <title><fmt:message key="locadora.lista"/></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  </head>
  <body>
    <div align="center">
      <h1><fmt:message key="locadora.disponivel"/></h1>
      <h2>
        <a href="/Bicicleta"><fmt:message key="menu"/></a>
      </h2>
    </div>
    <div align="center">
      <h2><fmt:message key="locadora.lista"/></h2>
      <form action="${pageContext.request.contextPath}/locadoras/buscaCidade?cidade=${locadora.cidade}" method="get">
        <label for="cidade"><fmt:message key="locadora.cidade"/>:</label>
        <input type="text" id="cidade" name="cidade">
        <button type="submit"><fmt:message key="buscar"/></button>
      </form>
      <c:if test="${not empty requestScope.listaLocadoras}">
        <table border="1">
          <tr>
            <th><fmt:message key="locadora.nome"/></th>   
            <th><fmt:message key="locadora.email"/></th>
            <th><fmt:message key="locadora.cnpj"/></th>
            <th><fmt:message key="locadora.cidade"/></th>
          </tr>
          <c:forEach var="locadora" items="${requestScope.listaLocadoras}">
            <tr>
              <td>${locadora.nome}</td>
              <td>${locadora.email}</td>
              <td>${locadora.cnpj}</td>
              <td>${locadora.cidade}</td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
      <c:if test="${empty requestScope.listaLocadoras}">
        <p><fmt:message key="locadora.vazio"/></p>
      </c:if>
    </div>
  </body>
</html>

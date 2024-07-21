<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<html>
  <head>
    <title><fmt:message key="locacao.titulo"/></title>
  </head>
  <body>
    <div align="center">
      <h1><fmt:message key="locacao.gerencia"/></h1>
      <h2><fmt:message key="locadora.ola"/></h2>
      <h2>
        <a href="/${requestScope.contextPath}/locadoras"><fmt:message key="menu"/></a>
        &nbsp;&nbsp;&nbsp;
        <a href="/${requestScope.contextPath}/locadoras/cadastro"><fmt:message key="locacao.novo"/></a>
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
              <th><fmt:message key="locacao.cpfCliente"/></th>
              <th><fmt:message key="locacao.cnpjLocadora"/></th>
              <th><fmt:message key="locacao.dataLocacao"/></th>
              <th><fmt:message key="acao"/></th>
            </tr>
            <c:forEach var="locacao" items="${requestScope.listaLocacoes}">
            <tr>
              <td>${locacao.cpfCliente}</td>
              <td>${locacao.cnpjLocadora}</td>  
              <td>${locacao.dataLocacao}</td>
              <td>
                <a href="/${requestScope.contextPath}/locadoras/edicao?id=${locacao.id}"><fmt:message key="editar"/></a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="/${requestScope.contextPath}/locadoras/remocao?id=${locacao.id}"onclick="return confirm(<fmt:message key="remover.confirma"/>);">
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

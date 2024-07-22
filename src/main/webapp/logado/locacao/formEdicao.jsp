<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />

<html>
  <head>
    <title><fmt:message key="locacao.edicao.titulo"/></title>
  </head>

  <body>
    <div align="center">
      <h1><fmt:message key="locacao.edicao"/></h1>
      <h2>
        <a href="lista"><fmt:message key="locacao.lista"/></a>
      </h2>
    </div>
    
    <div align="center">
      <c:if test="${mensagens.existeErros}">
        <ul>
          <c:forEach var="erro" items="${mensagens.erros}">
            <li>${erro}</li>
          </c:forEach>
        </ul>
      </c:if>
      
      <form action="atualize" method="get">
        <input type="hidden" name="id" value="${locacao.id}" />
        <label for="cpfCliente"><fmt:message key="locacao.cpfCliente"/></label>
        <input type="text" id="cpfCliente" name="cpfCliente" value="${locacao.cpfCliente}" required /><br/>
        
        <label for="cnpjLocadora"><fmt:message key="locacao.cnpjLocadora"/></label>
        <input type="text" id="cnpjLocadora" name="cnpjLocadora" value="${locacao.cnpjLocadora}" required /><br/>
        
        <label for="dataLocacao"><fmt:message key="locacao.dataLocacao"/></label>
        <input type="datetime-local" id="dataLocacao" name="dataLocacao" value="${locacao.dataLocacao}" required /><br/>
        
        <!-- Adicione mais campos conforme necessário -->
        
        <input type="submit" value="<fmt:message key='locacao.atualizar'/>" />
      </form>
    </div>
    
    <c:if test="${!empty requestScope.mensagens}">
      <ul class="erro">
        <c:forEach items="${requestScope.mensagens}" var="mensagem">
          <li>${mensagem}</li>
        </c:forEach>
      </ul>
    </c:if>
  </body>
</html>

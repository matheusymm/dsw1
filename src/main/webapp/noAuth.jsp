<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="../css/login.css" />
    <title><fmt:message key="cliente.login"/></title>
  </head>
  <body>
    <h1><fmt:message key="cliente.login"/></h1>
    <c:if test="${mensagens.existeErros}">
      <div id="erro">
        <ul>
          <c:forEach var="erro" items="${mensagens.erros}">
            <li>${erro}</li>
          </c:forEach>
        </ul>
      </div>
    </c:if>
  </body>
</html>

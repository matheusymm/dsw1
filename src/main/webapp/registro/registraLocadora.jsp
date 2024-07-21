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
    <title><fmt:message key="locadora.registro"/></title>
  </head>
  <body>
    <h1><fmt:message key="locadora.registro"/></h1>
    <c:if test="${mensagens.existeErros}">
      <div id="erro">
        <ul>
          <c:forEach var="erro" items="${mensagens.erros}">
            <li>${erro}</li>
          </c:forEach>
        </ul>
      </div>
    </c:if>
    <div class="form-container">
      <form method="post" action="${pageContext.request.contextPath}/locadoras/insercaoLocadora">
        <table>
          <tr>
            <th><fmt:message key="locadora.email"/>:</th>
            <td><input type="text" name="email" value="${param.email}" /></td>
          </tr>
          <tr>
            <th><fmt:message key="locadora.senha"/>:</th>
            <td><input type="password" name="senha" /></td>
          </tr>
          <tr>
            <th><fmt:message key="locadora.cnpj"/>:</th>
            <td><input type="text" name="cnpj" /></td>
          </tr>
          <tr>
            <th><fmt:message key="locadora.nome"/>:</th>
            <td><input type="text" name="nome" /></td>
          </tr>
          <tr>
            <th><fmt:message key="locadora.cidade"/>:</th>
            <td><input type="text" name="cidade" /></td>
          </tr>
          <tr>
            <td colspan="2">
              <button type="submit"><fmt:message key="registrar"/></button>
            </td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>

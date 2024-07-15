<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="./css/login.css" />
    <title>Autenticação de Cliente</title>
  </head>
  <body>
    <h1>Autenticação de Cliente</h1>
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
      <form method="post" action="index.jsp">
        <table>
          <tr>
            <th>E-mail:</th>
            <td><input type="text" name="email" value="${param.email}" /></td>
          </tr>
          <tr>
            <th>Senha:</th>
            <td><input type="password" name="senha" /></td>
          </tr>
          <tr>
            <td>
              <input type="submit" name="bOK" value="Entrar" />
            </td>
            <td>
              <input type="button" name="bRegistrar" value="Registrar" />
            </td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>

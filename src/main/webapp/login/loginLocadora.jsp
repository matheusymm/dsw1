<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="../css/login.css" />
    <title>Autenticação de Locadora</title>
  </head>
  <body>
    <h1>Autenticação de Locadora</h1>
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
      <form method="post" action="">
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
              <input type="submit" name="bIn" value="Entrar" />
            </td>
            <td>
              <input type="submit" name="bReg" value="Registrar" />
            </td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>

<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="../css/login.css" />
    <title>Cadastro de Cliente</title>
  </head>
  <body>
    <h1>Cadastro de Cliente</h1>
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
            <th>Nome:</th>
            <td><input type="text" name="nome" /></td>
          </tr>
          <tr>
            <th>CPF:</th>
            <td><input type="text" name="cpf" /></td>
          </tr>
          <tr>
            <th>Telefone:</th>
            <td><input type="text" name="telefone" /></td>
          </tr>
          <tr>
            <th>Sexo:</th>
            <td><input type="text" name="sexo" /></td>
          </tr>
          <tr>
            <th>Data de Nascimento:</th>
            <td><input type="date" name="dataNascimento" /></td>
          </tr>
          <tr>
            <td>
              <input type="submit" name="bOK" value="Registar" />
            </td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>

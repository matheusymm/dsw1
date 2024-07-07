<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
  <caption>
    <c:choose>
      <c:when test="${locadora != null}"> Edição </c:when>
      <c:otherwise> Cadastro </c:otherwise>
    </c:choose>
  </caption>
  <c:if test="${locadora != null}">
    <input type="hidden" name="id" value="${locadora.id}" />
  </c:if>
  <tr>
    <td><label for="email">E-mail</label></td>
    <td>
      <input
        type="email"
        id="email"
        name="email"
        size="45"
        required
        value="${locadora.email}"
      />
    </td>
  </tr>
  <tr>
    <td><label for="senha">Senha</label></td>
    <td>
      <input
        type="text"
        id="senha"
        name="senha"
        size="45"
        required
        value="${locadora.senha}"
      />
    </td>
  </tr>
  <tr>
    <td><label for="cnpj">CNPJ</label></td>
    <td>
      <input
        type="text"
        id="cnpj"
        name="cnpj"
        size="45"
        required
        value="${locadora.cnpj}"
      />
    </td>
  </tr>
  <tr>
    <td><label for="nome">Nome</label></td>
    <td>
      <input
        type="text"
        id="nome"
        name="nome"
        size="45"
        required
        value="${locadora.nome}"
      />
    </td>
  </tr>
  <tr>
    <td><label for="cidade">Cidade</label></td>
    <td>
      <input
        type="text"
        id="cidade"
        name="cidade"
        size="45"
        required
        value="${locadora.cidade}"
      />
    </td>
  </tr>

  <tr>
    <td colspan="2" align="center"><input type="submit" value="Salva" /></td>
  </tr>
</table>

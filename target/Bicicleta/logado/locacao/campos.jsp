<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table border="1">
  <caption>
    <c:choose>
      <c:when test="${locacao != null}"> Edição </c:when>
      <c:otherwise> Cadastro </c:otherwise>
    </c:choose>
  </caption>
  <c:if test="${locacao != null}">
    <input type="hidden" name="id" value="${locacao.id}" />
  </c:if>
  <tr>
    <td><label for="cpfCliente">CPF Cliente</label></td>
    <td>
      <input
        type="text"
        id="cpfCliente"
        name="cpfCliente"
        size="45"
        required
        value="${locacao.cpfCliente}"
      />
    </td>
  </tr>
  <tr>
    <td><label for="cnpjLocadora">CNPJ Locadora</label></td>
    <td>
      <input
        type="text"
        id="cnpjLocadora"
        name="cnpjLocadora"
        size="45"
        required
        value="${locacao.cnpjLocadora}"
      />
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input type="submit" value="Salva" /></td>
  </tr>
</table>

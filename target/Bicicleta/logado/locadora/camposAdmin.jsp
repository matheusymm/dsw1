<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<table border="1">
  <caption>
    <c:choose>
      <c:when test="${locadora != null}"> <fmt:message key="editar"/> </c:when>
      <c:otherwise> <fmt:message key="cadastro"/> </c:otherwise>
    </c:choose>
  </caption>
  <c:if test="${locadora != null}">
    <input type="hidden" name="id" value="${locadora.id}" />
  </c:if>
  <tr>
    <td><label for="email"><fmt:message key="locadora.email"/></label></td>
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
    <td><label for="senha"><fmt:message key="locadora.senha"/></label></td>
    <td>
      <input
        type="password"
        id="senha"
        name="senha"
        size="45"
        required
        value="${locadora.senha}"
      />
    </td>
  </tr>
  <tr>
    <td><label for="cnpj"><fmt:message key="locadora.cnpj"/></label></td>
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
    <td><label for="nome"><fmt:message key="locadora.nome"/></label></td>
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
    <td><label for="cidade"><fmt:message key="locadora.cidade"/></label></td>
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
    <td><label for="papel"><fmt:message key="locadora.papel"/></label></td>
    <td>
      <input
        type="text"
        id="papel"
        name="papel"
        size="45"
        required
        value="${locadora.papel}"
      />
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input type="submit" value=<fmt:message key="salvar"/> /></td>
  </tr>
</table>

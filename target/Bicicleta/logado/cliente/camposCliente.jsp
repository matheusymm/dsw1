<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ page isELIgnored="false"%> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<table border="1">
  <h3><fmt:message key="locacao.novo"/></h3>
  <c:if test="${locacao != null}">
    <input type="hidden" name="id" value="${locacao.id}" />
  </c:if>
  <tr>
    <td><label for="cpfCliente"><fmt:message key="locacao.cpfCliente"/></label></td>
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
    <td><label for="cnpjLocadora"><fmt:message key="locacao.cnpjLocadora"/></label></td>
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
    <td><label for="dataLocacao"><fmt:message key="locacao.dataLocacao"/></label></td>
    <td>
      <input
        type="datetime-local"
        id="dataLocacao"
        name="dataLocacao"
        size="45"
        required
        value="${locacao.dataLocacao}"
      />
    </td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input type="submit" value=<fmt:message key="salvar"/> /></td>
  </tr>
</table>

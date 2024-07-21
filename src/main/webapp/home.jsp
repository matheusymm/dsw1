<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${not empty param.language ? param.language : 'pt'}" />
<fmt:setBundle basename="message" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="./css/index.css" />
    <title><fmt:message key="sistema"/></title>
  </head>
  <body>
    <h1><fmt:message key="sistema"/></h1>
    <div class="form-container">
      <form method="get" action="">
        <label for="language"><fmt:message key="idioma" />:</label>
        <select name="language" id="language" onchange="this.form.submit()">
          <option value="en" ${param.language == 'en' ? 'selected' : ''}>English</option>
          <option value="pt" ${param.language == 'pt' ? 'selected' : ''}>Português</option>
        </select>
      </form>
    </div>
    <div class="nav-container">
        <nav>
            <a href="loginCliente"><fmt:message key="cliente.area"/></a>
            <a href="loginLocadora"><fmt:message key="locadora.area"/></a>
            <a href="locadoras/listaLocadoras"><fmt:message key="locadora.lista" /></a>
        </nav>
    </div>
  </body>
</html>

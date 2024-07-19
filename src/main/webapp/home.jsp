<%@ page contentType="text/html" pageEncoding="UTF-8"%> <%@ page
isELIgnored="false"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="./css/index.css" />
    <title>Sistema de Locação de Bicicletas</title>
  </head>
  <body>
    <h1>Sistema de Locação de Bicicletas</h1>
    <div class="nav-container">
      <nav>
		<a href="/${requestScope.contextPath}/clientes">Clientes</a>
        <a href="/${requestScope.contextPath}/locadoras">Locadoras</a>
        <a href="/${requestScope.contextPath}/locacoes">Locações</a>
      </nav>
    </div>
  </body>
</html>

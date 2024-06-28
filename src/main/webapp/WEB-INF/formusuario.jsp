<!DOCTYPE html>
<%@page import="br.com.locacao.persistencia.entidade.Usuario" %>
<html>

<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>

<body>
	<%
	Usuario u = (Usuario)request.getAttribute("usu");
	%>
	<form action="usucontroller.do" method="post">
		Nome:<input type="text" name="nome" value="<%=u.getNome()%>"/>
		CPF:<input type="text" name="CPF" value="<%=u.getCPF()%>"/>
		Login:<input type="text" name="login" value="<%=u.getLogin()%>"/>
		Senha:<input type="text" name="senha" value="<%=u.getSenha()%>"/>
		Sexo:<input type="text" name="sexo" value="<%=u.getSexo()%>"/>
		Telefone:<input type="text" name="telefone" value="<%=u.getTelefone()%>"/>


		<input type="submit" value="Salvar">

	</form>

</body>

</html>
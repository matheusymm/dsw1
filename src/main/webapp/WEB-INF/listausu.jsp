<%@ page import="br.com.locacao.persistencia.entidade.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuários</title>
<script type="text/javascript">
function confirmaExclusao(cpf){
	if (window.confirm('Tem certeza que deseja excluir?')){
		location.href="usucontroller.do?acao=exc&cpf="+cpf;
	}
}

</script>
</head>
<body>
<%
	//JSP's são servlets gerados dinamicamente
    // Corrigindo o tipo e a conversão de tipo
    List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
%>
	<table border="1">
	<tr> <th>CPF</th> <th>nome</th> <th>Ação</th> </tr>
	
<%	for(Usuario u : lista){ %>
		<tr>
			<td><%= u.getCPF() %></td> 
			<td><%= u.getNome() %></td>
			<td> <a href="javascript:confirmaExclusao(<%= u.getCPF() %>)">excluir</a> | <a href="usucontroller.do?acao=alt&cpf=<%=u.getCPF()%>"> alterar </a>  </td>
		</tr>
	<% } %>
	
	<a href="usucontroller.do?acao=cad"> Cadastrar novo usuário</a>
</body>
</html>
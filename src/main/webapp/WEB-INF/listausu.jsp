<%@ page import="br.com.locacao.persistencia.entidade.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        a {
            text-decoration: none;
            color: #007BFF;
        }
        a:hover {
            text-decoration: underline;
        }
        .action-links a {
            margin-right: 10px;
        }
    </style>
    <script type="text/javascript">
        function confirmaExclusao(cpf) {
            if (window.confirm('Tem certeza que deseja excluir?')) {
                location.href = "usucontroller.do?acao=exc&cpf=" + cpf;
            }
        }
    </script>
</head>
<body>
<%
    // Obtendo a lista de usuários da requisição
    List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
%>
    <h1>Lista de Usuários</h1>
    <table>
        <tr>
            <th>CPF</th>
            <th>Nome</th>
            <th>Ação</th>
        </tr>
        <% for (Usuario u : lista) { %>
        <tr>
            <td><%= u.getCPF() %></td>
            <td><%= u.getNome() %></td>
            <td class="action-links">
                <a href="javascript:confirmaExclusao('<%= u.getCPF() %>')">Excluir</a>
                <a href="usucontroller.do?acao=alt&cpf=<%= u.getCPF() %>">Alterar</a>
            </td>
        </tr>
        <% } %>
    </table>
    <a href="usucontroller.do?acao=cad">Cadastrar novo usuário</a>
</body>
</html>

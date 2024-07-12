<!DOCTYPE html>
<%@ page import="br.com.locacao.persistencia.entidade.Usuario" %>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Formulário de Usuário</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        form {
            max-width: 600px;
            margin: auto;
        }
        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            margin-top: 20px;
            padding: 10px 15px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>
    <%
        Usuario u = (Usuario) request.getAttribute("usu");
    %>
    <form action="CRUD" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" value="<%= u.getNome() %>" />

        <label for="cpf">CPF:</label>
        <input type="text" id="cpf" name="CPF" value="<%= u.getCPF() %>" />

        <label for="login">Login:</label>
        <input type="text" id="login" name="login" value="<%= u.getLogin() %>" />

        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" value="<%= u.getSenha() %>" />

        <label for="sexo">Sexo:</label>
        <input type="text" id="sexo" name="sexo" value="<%= u.getSexo() %>" />

        <label for="telefone">Telefone:</label>
        <input type="text" id="telefone" name="telefone" value="<%= u.getTelefone() %>" />

        <input type="submit" value="Salvar">
    </form>
</body>

</html>

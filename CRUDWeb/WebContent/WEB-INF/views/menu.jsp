<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Menu</title>
	</head>
	<body>
		<h2>Menu Inicial</h2>
		<p>Bem vindo, ${usuarioLogado.login}</p>
		<a href="listaAlunos">Lista de Alunos</a>
		<a href="listaFuncionarios">Lista de Funcionários</a>
		<a href="cadastroFuncionario">Cadastro de Funcionários</a>
		<br>
		<a href="logout">Logout</a>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="UTF-8"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Menu</title>
	</head>
	<body>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
    <div class="container-fluid">
		<h2 align="center">Menu Inicial</h2>
		<p style="font-size: 24px">Bem-vindo, ${usuarioLogado.login}!</p>
		<table cellpadding="5">
		<tr>
			<td><a class="btn btn-primary" href="listaAlunos">Lista de Alunos</a></td>
		</tr>
		<tr>
			<td><a class="btn btn-primary" href="listaFuncionarios">Lista de Funcionários</a></td>
		</tr>
		<tr>
			<td><a class="btn btn-primary" href="cadastroAluno">Cadastro de Alunos</a></td>
		</tr>
		<tr>
			<td><a class="btn btn-primary" href="cadastroFuncionario">Cadastro de Funcionários</a></td>
		</tr>
		<tr>
			<td><a class="btn btn-dark" href="logout">Logout</a></td>
		</tr>
		</table>
	</div>
	</body>
</html>
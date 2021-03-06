<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" href="css/formStyle.css">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Lista de Alunos</title>
	</head>
	<style>
		#status_ativo {
			color: green;
		}
		#status_suspenso {
			color: #FFA500;
		}
		#status_ferias {
			color: #FFA500;
		}
		#status_licenca {
			color: #FFA500;
		}
		#status_reprovado {
			color: red;
		}
		#status_inativo {
			color: red;
		}
		#filtro {
			margin-bottom: 3px;
		}
	</style>
	<body>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
    <div class="container-fluid">
		<h2 align="center">Tabela de Alunos</h2>
		Filtro:
		<select name="status" id="filtro">
			<option value="todos">Todos</option>
			<option value="1">Ativos</option>
			<option value="2">Suspensos</option>
			<option value="3">Ferias</option>
			<option value="4">Licença</option>
			<option value="5">Reprovados</option>
			<option value="6">Inativos</option>
		</select>
		<table class="greenTable" id="alunos">
		<thead>
			<tr>
				<th>Matrícula</th>
				<th>Nome</th>
				<th>CPF</th>
				<th>Data de Nascimento</th>
				<th>Sexo</th>
				<th>Telefone</th>
				<th>Endereço</th>
				<th>Curso</th>
				<th>Status</th>
				<th colspan="2" style="text-align: center">Ações</th>
			</tr>
		</thead>
			<c:forEach items="${alunos}" var="aluno">
			<tbody>
			<tr id="aluno_${aluno.matricula}">
				<td>${aluno.matricula}</td>
				<td>${aluno.nome}</td>
				<td>${aluno.cpf}</td>
				<td>
					<fmt:formatDate
					value="${aluno.dataNascimento}"
					pattern="dd/MM/yyyy"/>
				</td>
				<td>${aluno.sexo}</td>
				<td>${aluno.telefone}</td>
				<td>${aluno.endereco}</td>
				<td>${aluno.curso}</td>
				<c:if test="${aluno.status == '1'}">
					<td id="status_ativo">ATIVO</td>
				</c:if>
				<c:if test="${aluno.status == '2'}">
					<td id="status_suspenso">SUSPENSO</td>
				</c:if>
				<c:if test="${aluno.status == '3'}">
					<td id="status_ferias">REPROVADO</td>
				</c:if>
				<c:if test="${aluno.status == '4'}">
					<td id="status_licenca">FÉRIAS</td>
				</c:if>
				<c:if test="${aluno.status == '5'}">
					<td id="status_reprovado">LICENÇA</td>
				</c:if>
				<c:if test="${aluno.status == '6'}">
					<td id="status_inativo">INATIVO</td>
				</c:if>
				<td><a class="btn btn-danger" href="removeAluno?matricula=${aluno.matricula}">Remover</a></td>
				<td><a class="btn btn-primary" href="cadastroAluno?matricula=${aluno.matricula}">Alterar</a></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<br/>
		<button class="btn btn-primary" onclick='location.href="cadastroAluno";'>Novo Aluno</button>
		<a class="btn btn-dark" href="efetuaLogin">Voltar</a>
		</div>
		<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script>
		$("#filtro").on("change", function() {
				$.post("filtroAluno?status=" + $("#filtro").val(), function(resposta) {
					$("#alunos").html(resposta);
				})
		});
		</script>
	</body>
</html>
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
		<title>Lista de Alunos</title>
	</head>
	<style>
		#status_ativo {
			color: green;
		}
		#status_suspenso {
			color: #FFA500;
		}
		#status_reprovado {
			color: red;
		}
		#status_inativo {
			color: red;
		}
	</style>
	<body>
		<table class="greenTable">
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
				<c:if test="${aluno.status == 'ATIVO'}">
					<td id="status_ativo">${aluno.status}</td>
				</c:if>
				<c:if test="${aluno.status == 'SUSPENSO'}">
					<td id="status_suspenso">${aluno.status}</td>
				</c:if>
				<c:if test="${aluno.status == 'REPROVADO'}">
					<td id="status_reprovado">${aluno.status}</td>
				</c:if>
				<c:if test="${aluno.status == 'INATIVO'}">
					<td id="status_inativo">${aluno.status}</td>
				</c:if>
				<td><a href="removeAluno?matricula=${aluno.matricula}">Remover</a></td>
				<td><a href="cadastroAluno?matricula=${aluno.matricula}">Alterar</a></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<br/>
		<button onclick='location.href="cadastroAluno";'>Novo Aluno</button>
		<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script>
		</script>
	</body>
</html>
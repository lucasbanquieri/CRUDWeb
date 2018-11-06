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
		<title>Lista de Funcionarios</title>
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
		#funcionarios {
			width: 40%;
		}
	</style>
	<body>
		<h2 align="center">Tabela de Funcionarios</h2>
		Filtro:
		<select name="status" id="filtro">
			<option value="todos">Todos</option>
			<option value="ativo">Ativos</option>
			<option value="suspenso">Suspensos</option>
			<option value="ferias">Ferias</option>
			<option value="licenca">Licença</option>
			<option value="inativo">Inativos</option>
		</select>
		<table class="greenTable" id="funcionarios">
		<thead>
			<tr>
				<th>Código de Cadastro</th>
				<th>Nome</th>
				<th>CPF</th>
				<th>Data de Nascimento</th>
				<th>Sexo</th>
				<th>Telefone</th>
				<th>Endereço</th>
				<th>Cargo</th>
				<th>Disciplina</th>
				<th>Salário</th>
				<th>Vale Alimentação</th>
				<th>Vale Refeição</th>
				<th>Vale Transporte</th>
				<th>Nº Dependentes</th>
				<th>Status</th>
			</tr>
		</thead>
			<c:forEach items="${funcionarios}" var="funcionario">
			<tbody>
			<tr id="funcionario_${funcionario.codCadastro}">
				<td>${funcionario.codCadastro}</td>
				<td>${funcionario.nome}</td>
				<td>${funcionario.cpf}</td>
				<td>
					<fmt:formatDate
					value="${funcionario.dataNascimento}"
					pattern="dd/MM/yyyy"/>
				</td>
				<td>${funcionario.sexo}</td>
				<td>${funcionario.telefone}</td>
				<td>${funcionario.endereco}</td>
				<td>${funcionario.cargo}</td>
				<c:if test="${funcionario.cargo eq 'Professor'}">
					<td>${funcionario.disciplina}</td>
				</c:if>
				<c:if test="${funcionario.cargo != 'Professor'}">
					<td>N/A</td>
				</c:if>
				<td>R$ ${funcionario.salario}</td>
				<td>R$ ${funcionario.VA}</td>
				<td>R$ ${funcionario.VR}</td>
				<td>R$ ${funcionario.VT}</td>
				<td>${funcionario.kids}</td>
				<c:if test="${funcionario.status == 'ATIVO'}">
					<td id="status_ativo">${funcionario.status}</td>
				</c:if>
				<c:if test="${funcionario.status == 'SUSPENSO'}">
					<td id="status_suspenso">${funcionario.status}</td>
				</c:if>
				<c:if test="${funcionario.status == 'FERIAS'}">
					<td id="status_ferias">${funcionario.status}</td>
				</c:if>
				<c:if test="${funcionario.status == 'LICENCA'}">
					<td id="status_licenca">${funcionario.status}</td>
				</c:if>
				<c:if test="${funcionario.status == 'INATIVO'}">
					<td id="status_inativo">${funcionario.status}</td>
				</c:if>
				<td><a href="removeFuncionario?codCadastro=${funcionario.codCadastro}">Remover</a></td>
				<td><a href="cadastroFuncionario?codCadastro=${funcionario.codCadastro}">Alterar</a></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<br/>
		<button onclick='location.href="cadastroFuncionario";'>Novo Funcionário</button>
		<script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script>
		$("#filtro").on("change", function() {
				$.post("filtroFuncionario?status=" + $("#filtro").val(), function(resposta) {
					$("#funcionarios").html(resposta);
				});
		});
		</script>
	</body>
</html>
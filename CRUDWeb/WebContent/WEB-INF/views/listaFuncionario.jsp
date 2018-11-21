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
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
    <div class="container-fluid">
		<h2 align="center">Tabela de Funcionarios</h2>
		Filtro:
		<select name="status" id="filtro">
			<option value="todos">Todos</option>
			<option value="1">Ativos</option>
			<option value="2">Suspensos</option>
			<option value="4">Ferias</option>
			<option value="5">Licença</option>
			<option value="6">Inativos</option>
		</select>
		<table class="greenTable" id="funcionarios" cellpadding="15">
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
				<th colspan="2" style="text-align: center">Ações</th>
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
				<td>R$ ${funcionario.salarioStr}</td>
				<td>R$ ${funcionario.VAStr}</td>
				<td>R$ ${funcionario.VRStr}</td>
				<td>R$ ${funcionario.VTStr}</td>
				<td>${funcionario.kids}</td>
				<c:if test="${funcionario.status == '1'}">
					<td id="status_ativo">ATIVO</td>
				</c:if>
				<c:if test="${funcionario.status == '2'}">
					<td id="status_suspenso">SUSPENSO</td>
				</c:if>
				<c:if test="${funcionario.status == '4'}">
					<td id="status_ferias">FÉRIAS</td>
				</c:if>
				<c:if test="${funcionario.status == '5'}">
					<td id="status_licenca">LICENÇA</td>
				</c:if>
				<c:if test="${funcionario.status == '6'}">
					<td id="status_inativo">INATIVO</td>
				</c:if>
				<td><a class="btn btn-danger" href="removeFuncionario?codCadastro=${funcionario.codCadastro}">Remover</a></td>
				<td><a class="btn btn-primary" href="cadastroFuncionario?codCadastro=${funcionario.codCadastro}">Alterar</a></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<br/>
		<button class="btn btn-primary" onclick='location.href="cadastroFuncionario";'>Novo Funcionário</button>
		<a class="btn btn-dark" href="efetuaLogin">Voltar</a>
		</div>
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
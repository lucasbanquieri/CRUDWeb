<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<c:if test="${aluno.status == '1'}">
					<td id="status_ativo">ATIVO</td>
				</c:if>
				<c:if test="${aluno.status == '2'}">
					<td id="status_suspenso">SUSPENSO</td>
				</c:if>
				<c:if test="${aluno.status == '3'}">
					<td id="status_reprovado">REPROVADO</td>
				</c:if>
				<c:if test="${aluno.status == '4'}">
					<td id="status_inativo">FÉRIAS</td>
				</c:if>
				<c:if test="${aluno.status == '5'}">
					<td id="status_inativo">LICENÇA</td>
				</c:if>
				<c:if test="${aluno.status == '6'}">
					<td id="status_inativo">INATIVO</td>
				</c:if>
				<td><a href="removeAluno?matricula=${aluno.matricula}">Remover</a></td>
				<td><a href="cadastroAluno?matricula=${aluno.matricula}">Alterar</a></td>
			</tr>
			</c:forEach>
			</tbody>
</table>
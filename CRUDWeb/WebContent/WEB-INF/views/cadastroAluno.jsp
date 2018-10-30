<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastrar Aluno</title>
	</head>
	<style>
		td {
			text-align: left;
		}
	</style>
	<body>
		<input type="hidden" name="matricula" value="${aluno.matricula}">
		<h3 align="center">Cadastrar Aluno</h3>
		<form:errors path="*"/>
		<form align="center" action="adicionaAluno" method="post">
			<table align="center" border="0">
				<tr>
					<td>Nome: </td>
					<td><input class="nome" type="text" name="nome" min="1" max="50" value="${aluno.nome}" pattern="[A-Za-z]"><br /></td>
				</tr>
				<tr>
					<td>CPF: </td>
					<td><input class="cpf" type="number" name="cpf" max="11" value="${aluno.nome}" onkeydown="return FilterInput(event)" onpaste="handlePaste(event)"></td>
				</tr>
				<tr>
					<td>Sexo: </td>
					<td>
					<c:if test="${aluno.sexo eq 'M' or aluno.sexo eq null or aluno.sexo eq ''}">
						<input type="radio" name="sexo" value="M" checked>Masculino &nbsp
						<input type="radio" name="sexo" value="F">Feminino
					</c:if>
					<c:if test="${aluno.sexo eq 'F'}">
						<input type="radio" name="sexo" value="M">Masculino &nbsp
						<input type="radio" name="sexo" value="F" checked>Feminino
					</c:if>
					</td>
				</tr>
				<tr>
					<td>Data de Nascimento: </td>
					<td><input type="date" name="dataN" value='<fmt:formatDate value="${aluno.dataNascimento}" pattern="dd/MM/yyyy"/>'></td>
				</tr>
				<tr>
					<td>Telefone: </td>
					<td><input class="telefone" type="number" name="telefone" value="${aluno.telefone}" onkeydown="return FilterInput(event)" onpaste="handlePaste(event)"></td>
				</tr>
				<tr>
					<td>E-Mail: </td>
					<td><input class="email" type="text" name="email" value="${aluno.email}"></td>
				</tr>
				<tr>
					<td>Endereço: </td>
					<td><textarea class="endereco" name="endereco" minlength="1" maxlength="50" rows="3" cols="50" value="${aluno.endereco}"></textarea></td>
				</tr>
				<tr>
					<td>Curso: </td>
					<td>
						<select>
							<option value="Java Web" <c:if test="${aluno.curso eq 'Java Web' }">selected</c:if>>Java Web</option>
							<option value="Cobol" <c:if test="${aluno.curso eq 'Cobol' }">selected</c:if>>Cobol</option>
							<option value=".NET" <c:if test="${aluno.curso eq '.NET' }">selected</c:if>>.NET</option>
							<option value="Python" <c:if test="${aluno.curso eq 'Python' }">selected</c:if>>Python</option>
							<option value="Redes" <c:if test="${aluno.curso eq 'Redes' }">selected</c:if>>Redes</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><button id="salvar">Salvar</button></td>
				</tr>
			</table>
		</form>
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script>
			$(".nome").on("input", function(){
				if ($(this).val().length < 2 || $(this).val().length > 50) {
					$(this).css("border", "1px solid red");
				} else {
					$(this).css("border", "1px solid green");
				}
			})
			
			$(".cpf").on("input", function(){
				if ($(this).val().length < 11 || $(this).val().length > 11) {
					$(this).css("border", "1px solid red");
				} else {
					$(this).css("border", "1px solid green");
				}
			})
			
			$(".telefone").on("input", function(){
				if ($(this).val().length < 12 || $(this).val().length > 12) {
					$(this).css("border", "1px solid red");
				} else {
					$(this).css("border", "1px solid green");
				}
			})
			
			$(".email").on("input", function(){
				if ($(this).val().length < 5 || $(this).val().length > 30) {
					$(this).css("border", "1px solid red");
				} else {
					$(this).css("border", "1px solid green");
				}
			})
			
			$(".endereco").on("input", function(){
				if ($(this).val().length < 10 || $(this).val().length > 50) {
					$(this).css("border", "1px solid red");
				} else {
					$(this).css("border", "1px solid green");
				}
			})
			
			function FilterInput(event) {
    			var keyCode = ('which' in event) ? event.which : event.keyCode;

    			isNotWanted = (keyCode == 69 || keyCode == 101);
   	 			return !isNotWanted;
			};
			function handlePaste (e) {
    			var clipboardData, pastedData;

    			// Get pasted data via clipboard API
    			clipboardData = e.clipboardData || window.clipboardData;
   				pastedData = clipboardData.getData('Text').toUpperCase();

    			if(pastedData.indexOf('E')>-1) {
        			//alert('found an E');
        			e.stopPropagation();
        			e.preventDefault();
   				}
			};
		</script>
	</body>
</html>
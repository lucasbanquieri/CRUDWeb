<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Cadastrar Aluno</title>
	</head>
	<body>
		<h3 align="center">Cadastrar Aluno</h3>
		<form:errors path="*"/>
		<form align="center" action="adicionaAluno" method="post">
			<table>
				<tr>
					<td>Nome: </td>
					<td><input type="text" name="nome" min="1" max="255"><br /></td>
				</tr>
				<tr>
					<td>CPF: </td>
					<td><input type="number" name="cpf"></td>
				</tr>
				<tr>
					<td>Sexo: </td>
					<td>
						<input type="radio" name="sexo" value="M" checked>Masculino &nbsp
						<input type="radio" name="sexo" value="F">Feminino
					</td>
				</tr>
				<tr>
					<td>Data de Nascimento: </td>
					<td><input type="date" name="dataN"></td>
				</tr>
				<tr>
					<td>Telefone: </td>
					<td><input type="number" name="telefone"></td>
				</tr>
				<tr>
					<td>E-Mail: </td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Endereço: </td>
					<td><textarea name="endereco" rows="3" cols="50"></textarea></td>
				</tr>
				<tr>
					<td>Curso: </td>
					<td>
						<select>
							<option value="Java Web">Java Web</option>
							<option value="Cobol">Cobol</option>
							<option value=".NET">.NET</option>
							<option value="Python">Python</option>
							<option value="Redes">Redes</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><button id="cadastrar" disabled>Cadastrar</button></td>
				</tr>
			</table>
		</form>
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script>
			$(".descricao").on("input", function(){
				if ($(this).val().length < 5) {
					$(this).css("border", "1px solid red");
					$("#cadastrar").attr("disabled", "disabled");
				} else {
					$(this).css("border", "1px solid green");
					$("#cadastrar").removeAttr("disabled");
				}
			})
		</script>
	</body>
</html>
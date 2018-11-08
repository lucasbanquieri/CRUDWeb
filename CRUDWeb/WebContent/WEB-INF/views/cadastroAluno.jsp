<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<head>
		<meta charset="UTF-8"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/formStyle.css">
		<title>Cadastrar Aluno</title>
	</head>
	<style>
		td {
			text-align: left;
		}
	</style>
	<body>
		<h3 class="titulo" align="center">Cadastrar Aluno</h3>
		<form:errors path="*"/>
		<form align="center" action="adicionaAluno" method="post" accept-charset="UTF-8">
		<input type="hidden" id="matricula" name="matricula" value="${aluno.matricula}">
			<table align="center" border="0">
				<tr>
					<td hidden class="mat">Matrícula: </td>
					<td hidden class="mat"><input type="text" value="${aluno.matricula}" disabled></td>
				</tr>
				<tr>
					<td class="status" hidden>Status: </td>
					<td class="status" hidden>
						<select name="status">
							<option value="ATIVO" <c:if test="${aluno.status eq 'ATIVO' }">selected</c:if>>ATIVO</option>
							<option value="SUSPENSO" <c:if test="${aluno.status eq 'SUSPENSO' }">selected</c:if>>SUSPENSO</option>
							<option value="REPROVADO" <c:if test="${aluno.status eq 'REPROVADO' }">selected</c:if>>REPROVADO</option>
							<option value="INATIVO" <c:if test="${aluno.status eq 'INATIVO' }">selected</c:if>>INATIVO</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Nome: </td>
					<td>
						<input class="nome" type="text" name="nome" minlength="1" maxlength="50" value="${aluno.nome}"><br />
						<span id="nome_erro" class="validacao"></span>
					</td>
				</tr>
				<tr>
					<td>CPF: </td>
					<td>
						<input class="cpf" type="text" name="cpf" maxlength="14" value="${aluno.cpf}" onkeydown="return FilterInput(event)" onpaste="handlePaste(event)" <c:if test="${funcionario.codCadastro > 0}">readonly</c:if>>
						<span id="cpf_erro" class="validacao"></span>
					</td>
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
					<td><input type="date" class="dataN" name="dataNascimentoStr" value='<fmt:formatDate value="${aluno.dataNascimento}" pattern="yyyy-MM-dd"/>' pattern="dd/MM/yyyy"></td>
				</tr>
				<tr>
					<td>Telefone: </td>
					<td>
						<input class="telefone" type="text" name="telefone" minlenght="15" maxlength="17" value="${aluno.telefone}" onkeydown="return FilterInput(event)" onpaste="handlePaste(event)">
						<span id="telefone_erro" class="validacao"></span>
					</td>
				</tr>
				<tr>
					<td>E-Mail: </td>
					<td>
						<input class="email" type="text" name="email" value="${aluno.email}">
						<span id="email_erro" class="validacao"></span>
					</td>
				</tr>
				<tr>
					<td>Endereço: </td>
					<td>
						<textarea class="endereco" name="endereco" minlength="1" maxlength="50" rows="3" cols="50">${aluno.endereco}</textarea>
						<span id="endereco_erro" class="validacao"></span>
					</td>
				</tr>
				<tr>
					<td>Curso: </td>
					<td>
						<select name="curso">
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
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
		<script>
    		$(document).ready(function () { 
       			var $seuCampoCpf = $(".cpf");
        		$seuCampoCpf.mask('000.000.000-00', {reverse: true});
   		 	});
    		
    		$(document).ready(function () { 
        		$(".telefone").mask('(00) 0000-0000');
   		 	});
		</script>
		<script>
			var erro_nome = true;
			var erro_cpf = true;
			var erro_telefone = true;
			var erro_email = true;
			var erro_endereco = true;
			
			if ($("#matricula").val() > 0) {
				var erro_nome = false;
				var erro_cpf = false;
				var erro_telefone = false;
				var erro_email = false;
				var erro_endereco = false;
				$(".titulo").html("Alterar Aluno");
				$(".mat").removeAttr("hidden");
				$(".status").removeAttr("hidden");
			}
			
			$(".nome").on("input", function(){
				if ($(this).val().length < 2 || $(this).val().length > 50) {
					this.value = this.value.replace(/[^A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]/g,'');
					$(this).css("border", "1px solid red");
					$("#nome_erro").html("Nome inválido.");
					$("#nome_erro").css("color", "red");
					erro_nome = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#nome_erro").html("");
					erro_nome = false;
				}
			})
			
			$(function() {
    			//Executa a requisição quando o campo username perder o foco
    			$('.cpf').blur(function()
    		{
        	var cpf = $('.cpf').val().replace(/[^0-9]/g, '').toString();

        	if( cpf.length == 11 ) {
            	var v = [];

            	//Calcula o primeiro dígito de verificação.
            	v[0] = 1 * cpf[0] + 2 * cpf[1] + 3 * cpf[2];
            	v[0] += 4 * cpf[3] + 5 * cpf[4] + 6 * cpf[5];
            	v[0] += 7 * cpf[6] + 8 * cpf[7] + 9 * cpf[8];
            	v[0] = v[0] % 11;
            	v[0] = v[0] % 10;

            	//Calcula o segundo dígito de verificação.
            	v[1] = 1 * cpf[1] + 2 * cpf[2] + 3 * cpf[3];
            	v[1] += 4 * cpf[4] + 5 * cpf[5] + 6 * cpf[6];
            	v[1] += 7 * cpf[7] + 8 * cpf[8] + 9 * v[0];
            	v[1] = v[1] % 11;
            	v[1] = v[1] % 10;

            	//Retorna Verdadeiro se os dígitos de verificação são os esperados.
            	if ( (v[0] != cpf[9]) || (v[1] != cpf[10]) ) {
            		$(".cpf").css("border", "1px solid red");
            		erro_cpf = true;
            		$("#cpf_erro").html("CPF inválido.");
					$("#cpf_erro").css("color", "red");

                	$('.cpf').val($('.cpf').val());
            	} else {
            		$(".cpf").css("border", "1px solid green");
            		$("#cpf_erro").html("");
            		erro_cpf = false;
            	}
        	} else {
        		$(".cpf").css("border", "1px solid red");
        		erro_cpf = true;
        		$("#cpf_erro").html("CPF deve conter 11 dígitos.");
				$("#cpf_erro").css("color", "red");

				$('.cpf').val($('.cpf').val());
        	}
    	});
	});
			
			
			
			$(".telefone").on("input", function(){
				if ($(this).val().length < 14 || $(this).val().length > 18) {
					$(this).css("border", "1px solid red");
					$("#telefone_erro").html("Telefone inválido.");
					$("#telefone_erro").css("color", "red");
					erro_telefone = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#telefone_erro").html("");
					erro_telefone = false;
				}
			})
			
			$(".email").on("input", function(){
				if ($(this).val().length < 5 || $(this).val().length > 30) {
					$(this).css("border", "1px solid red");
					$("#email_erro").html("E-mail inválido.");
					$("#email_erro").css("color", "red");
					erro_email = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#email_erro").html("");
					erro_email = false;
				}
			})
			
			$(".endereco").on("input", function(){
				if ($(this).val().length < 10 || $(this).val().length > 50) {
					$(this).css("border", "1px solid red");
					$("#endereco_erro").html("Endereço inválido.");
					$("#endereco_erro").css("color", "red");
					erro_endereco = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#endereco_erro").html("");
					erro_endereco = false;
				}
			})
			
			$("#salvar").on("click", function checarErro() {
				if (erro_nome || erro_cpf || erro_telefone || erro_email || erro_endereco) {
					alert("Campos não preenchidos ou inválidos.");
					return false;
				} else if ($(".dataN").val() == "") {
					alert("Data de nascimento não preenchida.");
					return false;
				}
			})
		</script>
	</body>
</html>
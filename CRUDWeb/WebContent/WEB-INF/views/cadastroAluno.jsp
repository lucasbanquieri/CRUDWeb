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
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>Cadastrar Aluno</title>
	</head>
	<body>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<div id="main" class="container-fluid">
		<h3 class="titulo" align="center">Cadastrar Aluno</h3>
		<form action="adicionaAluno" method="post" accept-charset="UTF-8">
			<input type="hidden" id="matricula" name="matricula" value="${aluno.matricula}">
			<div class="row">
                    <div class="form-group col-md-2">
                        <label hidden id="mat_label" for="campoMat">Matrícula:</label>
                        <input hidden class="form-control" id="mat" type="text" value="${aluno.matricula}" readonly>
                    </div>
					<div class="form-group col-md-2">
                        <label hidden id="status_label" for="campoStatus">Status:</label>
                        <select hidden class="form-control" id="status" name="status">
							<option value="1" <c:if test="${aluno.status eq '1' }">selected</c:if>>ATIVO</option>
							<option value="2" <c:if test="${aluno.status eq '2' }">selected</c:if>>SUSPENSO</option>
							<option value="3" <c:if test="${aluno.status eq '3' }">selected</c:if>>REPROVADO</option>
							<option value="4" <c:if test="${aluno.status eq '4' }">selected</c:if>>FÉRIAS</option>
                            <option value="5" <c:if test="${aluno.status eq '5' }">selected</c:if>>LICENÇA</option>
                            <option value="6" <c:if test="${aluno.status eq '6' }">selected</c:if>>INATIVO</option>
						</select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="campoNome">Nome:</label>
                        <input class="form-control" id="nome" type="text" name="nome" minlength="1" maxlength="50" value="${aluno.nome}">
                        <span id="nome_erro"></span>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="campoCpf">CPF:</label>
                        <input class="form-control" id="cpf" type="text" name="cpf" maxlength="14" <c:if test="${aluno.matricula > 0}">readonly</c:if> value="${aluno.cpf}">
                        <span id="cpf_erro"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="campoDataN">Data de Nascimento:</label>
                        <input type="date" class="form-control" id="dataN" name="dataNascimentoStr" value='<fmt:formatDate value="${aluno.dataNascimento}" pattern="yyyy-MM-dd"/>' pattern="dd/MM/yyyy">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="campoSexo">Sexo:</label><br>
                        <c:if test="${aluno.sexo eq 'M' or aluno.sexo eq null or aluno.sexo eq ''}">
                            <input type="radio" id="campo1" value="M" name="sexo" checked> Masculino <br/>
                            <input type="radio" id="campo1" value="F" name="sexo"> Feminino
                        </c:if>
                        <c:if test="${aluno.sexo eq 'F'}">
                            <input type="radio" id="campo1" value="M" name="sexo"> Masculino <br/>
                            <input type="radio" id="campo1" value="F" name="sexo" checked> Feminino
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="campoTelefone">Telefone:</label>
                        <input class="form-control" id="telefone" type="text" name="telefone" minlenght="15" maxlength="17" value="${aluno.telefone}">
                        <span id="telefone_erro"></span>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="campoEmail">E-Mail:</label>
                        <input class="form-control" id="email" type="email" name="email" value="${aluno.email}">
                        <span id="email_erro"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="campoEndereco">Endereço:</label>
                        <textarea class="form-control" id="endereco" name="endereco" minlength="1" maxlength="50" rows="3" cols="50">${aluno.endereco}</textarea>
                        <span id="endereco_erro"></span>
                    </div>
                </div>
                <div class="row">
					<div id="disciplina" class="form-group col-md-2">
	                      <label for="campoDisciplina">Curso:</label>
	                       <select class="form-control" name="curso" id="curso">
	                            <option>Selecionar Curso..</option>
	                            <option value="Java Web" <c:if test="${aluno.curso eq 'Java Web' }">selected</c:if>>Java Web</option>
	                            <option value="Cobol" <c:if test="${aluno.curso eq 'Cobol' }">selected</c:if>>Cobol</option>
								<option value=".NET" <c:if test="${aluno.curso eq '.NET' }">selected</c:if>>.NET</option>
								<option value="Python" <c:if test="${aluno.curso eq 'Python' }">selected</c:if>>Python</option>
								<option value="Redes" <c:if test="${aluno.curso eq 'Redes' }">selected</c:if>>Redes</option>
	                       </select>
	                </div>
                </div>
                <hr/>
                <div id="actions" class="row">
                    <div class="col-md-12">
                        <button id="salvar" type="submit" class="btn btn-primary">Salvar</button>
                        <a href="listaAlunos" class="btn btn-dark">Cancelar</a>
                    </div>
                </div>
			</form>
		</div>
		<script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
		<script>
    		$(document).ready(function () { 
       			var $seuCampoCpf = $("#cpf");
        		$seuCampoCpf.mask('000.000.000-00', {reverse: true});
   		 	});
    		
    		$(document).ready(function () { 
        		$("#telefone").mask('(00) 0000-0000');
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
				$("#mat_label").removeAttr("hidden");
				$("#mat").removeAttr("hidden");
				$("#status_label").removeAttr("hidden");
				$("#status").removeAttr("hidden");
			}
			
			$("#nome").on("input", function(){
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
    			$('#cpf').blur(function()
    		{
        	var cpf = $('#cpf').val().replace(/[^0-9]/g, '').toString();

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
            		$("#cpf").css("border", "1px solid red");
            		erro_cpf = true;
            		$("#cpf_erro").html("CPF inválido.");
					$("#cpf_erro").css("color", "red");

                	$('#cpf').val($('#cpf').val());
            	} else {
            		$("#cpf").css("border", "1px solid green");
            		$("#cpf_erro").html("");
            		erro_cpf = false;
            	}
        	} else {
        		$("#cpf").css("border", "1px solid red");
        		erro_cpf = true;
        		$("#cpf_erro").html("CPF deve conter 11 dígitos.");
				$("#cpf_erro").css("color", "red");

				$('#cpf').val($('#cpf').val());
        	}
    	});
	});
			
			
			
			$("#telefone").on("input", function(){
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
			
			$("#email").on("input", function(){
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
			
			$("#endereco").on("input", function(){
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
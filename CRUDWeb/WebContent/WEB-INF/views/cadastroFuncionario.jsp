<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head>
        <title>Cadastro de Funcionarios</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="js/jquery.livejquery.js"></script>
        <div id="main" class="container-fluid">
            <h2 id="titulo" class="page-header" align="center">Cadastro de Funcionário</h2>
            <h4 class="page-header">Informações Pessoais</h4>
            <form:errors path="*"/>
            <form action="adicionaFuncionario" method="post" accept-charset="UTF-8">
            <input type="hidden" id="cod_cadastro" name="codCadastro" value="${funcionario.codCadastro}">
            <input type="hidden" id="qtd_dep" name="kids" value="${funcionario.kids}">
                <div class="row">
                    <div class="form-group col-md-2">
                        <label hidden id="cod_label" for="campoCod">Código de Cadastro:</label>
                        <input hidden class="form-control" id="cod" type="text" value="${funcionario.codCadastro}" disabled>
                    </div>
                    <div class="form-group col-md-2">
                        <label hidden id="status_label" for="campoStatus">Status:</label>
                        <select hidden class="form-control" id="status" name="status">
							<option value="1" <c:if test="${funcionario.status eq 'ATIVO' }">selected</c:if>>ATIVO</option>
							<option value="2" <c:if test="${funcionario.status eq 'SUSPENSO' }">selected</c:if>>SUSPENSO</option>
							<option value="3" <c:if test="${funcionario.status eq 'FERIAS' }">selected</c:if>>FÉRIAS</option>
                            <option value="4" <c:if test="${funcionario.status eq 'LICENCA' }">selected</c:if>>LICENÇA</option>
                            <option value="5" <c:if test="${funcionario.status eq 'INATIVO' }">selected</c:if>>INATIVO</option>
						</select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="campoNome">Nome:</label>
                        <input class="form-control" id="nome" type="text" name="nome" minlength="1" maxlength="50" value="${funcionario.nome}">
                        <span id="nome_erro"></span>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="campoCpf">CPF:</label>
                        <input class="form-control" id="cpf" type="text" name="cpf" maxlength="14" <c:if test="${funcionario.codCadastro > 0}">readonly</c:if> value="${funcionario.cpf}">
                        <span id="cpf_erro"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="campoDataN">Data de Nascimento:</label>
                        <input type="date" class="form-control" id="dataN" name="dataNascimentoStr" value='<fmt:formatDate value="${funcionario.dataNascimento}" pattern="yyyy-MM-dd"/>' pattern="dd/MM/yyyy">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="campoSexo">Sexo:</label><br>
                        <c:if test="${funcionario.sexo eq 'M' or funcionario.sexo eq null or funcionario.sexo eq ''}">
                            <input type="radio" id="campo1" value="M" name="sexo" checked> Masculino <br/>
                            <input type="radio" id="campo1" value="F" name="sexo"> Feminino
                        </c:if>
                        <c:if test="${funcionario.sexo eq 'F'}">
                            <input type="radio" id="campo1" value="M" name="sexo"> Masculino <br/>
                            <input type="radio" id="campo1" value="F" name="sexo" checked> Feminino
                        </c:if>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="campoTelefone">Telefone:</label>
                        <input class="form-control" id="telefone" type="text" name="telefone" minlenght="15" maxlength="17" value="${funcionario.telefone}">
                        <span id="telefone_erro"></span>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="campoEmail">E-Mail:</label>
                        <input class="form-control" id="email" type="email" name="email" value="${funcionario.email}">
                        <span id="email_erro"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="campoEndereco">Endereço:</label>
                        <textarea class="form-control" id="endereco" name="endereco" minlength="1" maxlength="50" rows="3" cols="50">${funcionario.endereco}</textarea>
                        <span id="endereco_erro"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-1">
                    <button class="btn btn-primary" id="addDep">Adicionar Dependente</button>
                    </div>
                </div>
                <h4 class="page-header">Dependentes</h4>
                <hr />
                <div class="row linhaKid modelo">
                	<div class="form-group col-md-3">
                		<label>Nome:</label>
                		<input type="text" name="nomeK" class="form-control nomeK" value="">
                		<span name="nomeK_erro"></span>
                	</div>
                	<div class="form-group col-md-3">
                		<label>Data de Nascimento:</label>
                		<input type="date" name="dataNK" class="form-control dataNK" value="" pattern="dd/MM/yyyy">
                	</div>
                	<div class="form-group col-md-1">
                		<span style="display: none; margin-top: 33px;" id="excluirDep" onClick="excluiDep(this)" class="form-control btn btn-danger">Remover</span>
                	</div>
                </div>
                <div class="fim"></div>
                <hr />
                <h4 class="page-header">Informações do Cargo</h4>
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="campoSalario">Salário(R$):</label>
                        <input class="form-control" id="salario" type="text" name="salarioStr" maxlength="12" value="<c:if test='${funcionario.codCadastro > 0}'>${funcionario.salarioStr}</c:if>">
                    	<span id="salario_erro"></span>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="campoVa">Vale Alimentação(R$):</label>
                        <input class="form-control" id="va" type="text" name="VAStr" maxlength="12" value="<c:if test='${funcionario.codCadastro > 0}'>${funcionario.VAStr}</c:if>">
                    	<span id="va_erro"></span>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="campoVr">Vale Refeição(R$):</label>
                        <input class="form-control" id="vr" type="text" name="VRStr" maxlength="12" value="<c:if test='${funcionario.codCadastro > 0}'>${funcionario.VRStr}</c:if>">
                    	<span id="vr_erro"></span>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="campoVt">Vale Transporte(R$):</label>
                        <input class="form-control" id="vt" type="text" name="VTStr" maxlength="12" value="<c:if test='${funcionario.codCadastro > 0}'>${funcionario.VTStr}</c:if>">
                    	<span id="vt_erro"></span>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-2">
                        <label for="campoCargo">Cargo:</label>
                        <select class="form-control" name="cargo" id="cargo">
                            <option>Selecionar Cargo..</option>
                            <option value="Professor" <c:if test="${funcionario.cargo eq 'Professor' }">selected</c:if>>Professor</option>
                            <option value="Analista Mainframe" <c:if test="${funcionario.cargo eq 'Analista Mainframe' }">selected</c:if>>Analista Mainframe</option>
                            <option value="Analista Baixa Plataforma" <c:if test="${funcionario.cargo eq 'Analista Baixa Plataforma' }">selected</c:if>>Analista Baixa Plataforma</option>
                            <option value="Programador Mainframe" <c:if test="${funcionario.cargo eq 'Programador Mainframe' }">selected</c:if>>Programador Mainframe</option>
                            <option value="Programador Baixa Plataforma" <c:if test="${funcionario.cargo eq 'Programador Baixa Plataforma' }">selected</c:if>>Programador Baixa Plataforma</option>
                            <option value="Líder de Projetos" <c:if test="${funcionario.cargo eq 'Líder de Projetos' }">selected</c:if>>Líder de Projetos</option>
                            <option value="Gerente" <c:if test="${funcionario.cargo eq 'Gerente' }">selected</c:if>>Gerente</option>
                        </select>
                    </div>
                    <div id="disciplina" class="form-group col-md-2">
                        <label for="campoDisciplina">Disciplina:</label>
                        <select class="form-control" name="disciplina" id="disc">
                            <option>Selecionar Disciplina..</option>
                            <option value="Java Web" <c:if test="${funcionario.disciplina eq 'Java Web' }">selected</c:if>>Java Web</option>
                            <option value="Cobol" <c:if test="${funcionario.disciplina eq 'Cobol' }">selected</c:if>>Cobol</option>
							<option value=".NET" <c:if test="${funcionario.disciplina eq '.NET' }">selected</c:if>>.NET</option>
							<option value="Python" <c:if test="${funcionario.disciplina eq 'Python' }">selected</c:if>>Python</option>
							<option value="Redes" <c:if test="${funcionario.disciplina eq 'Redes' }">selected</c:if>>Redes</option>
                        </select>
                    </div>
                </div>
                <hr />
                <div id="actions" class="row">
                    <div class="col-md-12">
                        <button id="salvar" type="submit" class="btn btn-primary">Salvar</button>
                        <a href="listaFuncionarios" class="btn btn-dark">Cancelar</a>
                    </div>
                </div>
            </form>
            <br/>
        </div>
        <script src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        <script>
        	//////////////MÁSCARAS/////////////
    		$(document).ready(function () { 
       			var $seuCampoCpf = $("#cpf");
        		$seuCampoCpf.mask('000.000.000-00', {reverse: true});
        		$("#telefone").mask('(00) 0000-0000');
        		$("#salario").mask('000.000.000.000.000,00', {reverse: true});
        		$("#va").mask('000.000.000.000.000,00', {reverse: true});
        		$("#vr").mask('000.000.000.000.000,00', {reverse: true});
        		$("#vt").mask('000.000.000.000.000,00', {reverse: true});
    			if ($("#cargo").val() != "Professor") {
        			$("#disciplina").hide();
    			}
   		 	});
		</script>
		<script src="js/jquery.livejquery.js"></script>
		<script type="text/javascript">
			$(document).ready(function(e) {
				$("#addDep").on("click", function(e) {
					var novaLinha = $(".modelo").clone();
					novaLinha.removeClass("modelo").addClass("nova").find("input[name=nome]").focus();
					novaLinha.find("#excluirDep").css("display", "inline-block");
					
					novaLinha.insertBefore(".fim");
					
					novaLinha.find(".nomeK").val("");
					novaLinha.find(".dataNK").val("");
					novaLinha.find(".nomeK").css("border", "1px solid #ced4da");
					
					return false;
				})
				var pv = true;
				
				if ($("#qtd_dep").val() > 0) {
					<c:forEach items="${kid}" var="kid">
						if (pv) {
							$(".nomeK").val("${kid.nome}");
							$(".dataNK").val('<fmt:formatDate value="${kid.dataNascimento}" pattern="yyyy-MM-dd"/>');
							pv = false;
						} else {
							var novaLinha = $(".modelo").clone();
							novaLinha.removeClass("modelo").addClass("nova").find("input[name=nome]").focus();
							novaLinha.find("#excluirDep").css("display", "inline-block");
							
							novaLinha.insertBefore(".fim");
							
							novaLinha.find(".nomeK").val("${kid.nome}");
							novaLinha.find(".dataNK").val('<fmt:formatDate value="${kid.dataNascimento}" pattern="yyyy-MM-dd"/>');
						}
					</c:forEach>
				}
			})
			
			function excluiDep(elemento) {
				elemento.closest(".nova").remove();
			}
		</script>
		<script>
			//VARIÁBEIS INICIAIS
			var erro_nome = true;
			var erro_cpf = true;
			var erro_telefone = true;
			var erro_email = true;
			var erro_endereco = true;
			var erro_salario = true;
			var erro_va = true;
			var erro_vr = true;
			var erro_vt = true;
			var erro_nomeK = false;
			var erro_cargo = true;
			
			//VERIFICA SE É UMA ALTERAÇÃO
			if ($("#cod_cadastro").val() > 0) {
				var erro_nome = false;
				var erro_cpf = false;
				var erro_telefone = false;
				var erro_email = false;
				var erro_endereco = false;
				var erro_salario = false;
				var erro_va = false;
				var erro_vr = false;
				var erro_vt = false;
				//var erro_nomeK = true;
				var erro_cargo = false;
				$("#titulo").html("Alterar Funcionario");
				$("#cod_label").removeAttr("hidden");
                $("#cod").removeAttr("hidden");
                $("#status_label").removeAttr("hidden");
				$("#status").removeAttr("hidden");
			}
			
			//VALIDAÇÕES
			$("#cargo").on("change", function() {
    			if ($(this).val() == "Professor") {
    				$("#disciplina").show();
    			} else {
    				$("#disciplina").hide();
    			}
    		})
			
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
			
			$(".nomeK").livequery("input", function(){
					if ($(this).val().length == 0) {
						$(this).css("border", "1px solid #ced4da");
						$(this).closest(".linhaKid").find("span[name=nomeK_erro]").html("");
					} else if ($(this).val().length < 2 || $(this).val().length > 50) {
						var input = $(this).val();
						input = input.replace(/[^A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]/g,'');
						$(this).val(input);
						$(this).css("border", "1px solid red");
						$(this).closest(".linhaKid").find("span[name=nomeK_erro]").html("Nome inválido.");
						$(this).closest(".linhaKid").find("span[name=nomeK_erro]").css("color", "red");
					} else {
						$(this).css("border", "1px solid green");
						$(this).closest(".linhaKid").find("span[name=nomeK_erro]").html("");
					}
			})
			
			$(function() {
    			$('#cpf').blur(function() {
        			var cpf = $('#cpf').val().replace(/[^0-9]/g, '').toString();

        			if( cpf.length == 11 ) {
            			var v = [];

		            	v[0] = 1 * cpf[0] + 2 * cpf[1] + 3 * cpf[2];
		            	v[0] += 4 * cpf[3] + 5 * cpf[4] + 6 * cpf[5];
		            	v[0] += 7 * cpf[6] + 8 * cpf[7] + 9 * cpf[8];
		            	v[0] = v[0] % 11;
		            	v[0] = v[0] % 10;

		            	v[1] = 1 * cpf[1] + 2 * cpf[2] + 3 * cpf[3];
		            	v[1] += 4 * cpf[4] + 5 * cpf[5] + 6 * cpf[6];
		            	v[1] += 7 * cpf[7] + 8 * cpf[8] + 9 * v[0];
		            	v[1] = v[1] % 11;
		            	v[1] = v[1] % 10;

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
			
			$("#salario").on("input", function(){
				if ($(this).val() < 0 || $(this).val() == "") {
					$(this).css("border", "1px solid red");
					$("#salario_erro").html("Salário inválido.");
					$("#salario_erro").css("color", "red");
					erro_salario = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#salario_erro").html("");
					erro_salario = false;
				}
			})
			
			$("#va").on("input", function(){
				if ($(this).val() < 0 || $(this).val() == "") {
					$(this).css("border", "1px solid red");
					$("#va_erro").html("Vale alimentação inválido.");
					$("#va_erro").css("color", "red");
					erro_va = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#va_erro").html("");
					erro_va = false;
				}
			})
			
			$("#vr").on("input", function(){
				if ($(this).val() < 0 || $(this).val() == "") {
					$(this).css("border", "1px solid red");
					$("#vr_erro").html("Vale refeição inválido.");
					$("#vr_erro").css("color", "red");
					erro_vr = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#vr_erro").html("");
					erro_vr = false;
				}
			})
			
			$("#vt").on("input", function(){
				if ($(this).val() < 0 || $(this).val() == "") {
					$(this).css("border", "1px solid red");
					$("#vt_erro").html("Vale transporte inválido.");
					$("#vt_erro").css("color", "red");
					erro_vt = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#vt_erro").html("");
					erro_vt = false;
				}
			})
			
			$("#dependentes").on("input", function(){
				if ($(this).val() < 0 || $(this).val() > 20 || $(this).val() == "") {
					$(this).css("border", "1px solid red");
					$("#kids_erro").html("Número de dependentes inválido.");
					$("#kids_erro").css("color", "red");
				} else {
					$(this).css("border", "1px solid green");
					$("#kids_erro").html("");
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
					$("#encereco_erro").css("color", "red");
					erro_endereco = true;
				} else {
					$(this).css("border", "1px solid green");
					$("#endereco_erro").html("");
					erro_endereco = false;
				}
			})
			
			$("#salvar").on("click", function() {
				if (erro_nome || erro_cpf || erro_telefone || erro_email || erro_endereco || erro_salario || erro_va || erro_vr || erro_vt) {
					alert("Campos não preenchidos ou inválidos.");
					if (erro_nome) {
						$("#nome").css("border", "1px solid red");
					} if (erro_cpf) {
						$("#cpf").css("border", "1px solid red");
					} if (erro_telefone) {
						$("#telefone").css("border", "1px solid red");
					} if (erro_email) {
						$("#email").css("border", "1px solid red");
					} if (erro_endereco) {
						$("#endereco").css("border", "1px solid red");
					} if (erro_salario) {
						$("#salario").css("border", "1px solid red");
					} if (erro_va) {
						$("#va").css("border", "1px solid red");
					} if (erro_vr) {
						$("#vr").css("border", "1px solid red");
					} if (erro_vt) {
						$("#vt").css("border", "1px solid red");
					}
					return false;
				} else if ($("#dataN").val() == "") {
					alert("Data de nascimento não preenchida.");
					return false;
				} else if ($("#cargo").val() == "Selecionar Cargo..") {
					alert("Selecione um cargo.");
					return false;
				} else if ($(".linhaKid").length > 1) {
					$(".linhaKid").each(function() {
						if ($(this).find(".nomeK").val() == "" || $(this).find(".nomeK").length < 3) {
							alert("Dependente inválido.");
							erro_nomeK = true;
							return false;
						}
					})
					
					if (erro_nomeK) {
						return false;
					}
					
					$("#qtd_dep").val($(".linhaKid").length);				
					
				} else if ($(".linhaKid").length == 1) {
					if ($(".linhaKid").find(".nomeK").val() == "") {
						$("#qtd_dep").val(0);
					} else {
						$("#qtd_dep").val(1);	
					}
				}
				
				if ($("#cargo").val() == "Professor" && $("#disc").val() == "Selecionar Disciplina..") {
					alert("Selecione uma disciplina.");
					return false;
				} 
			})
		</script>
    </body>
</html>
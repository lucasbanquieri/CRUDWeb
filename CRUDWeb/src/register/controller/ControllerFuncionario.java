package register.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import register.dao.FuncionarioDAO;
//import register.model.Aluno;
import register.model.Funcionario;

@Controller
public class ControllerFuncionario {
    @RequestMapping("/cadastroFuncionario")
	public String form(Funcionario funcionario, Model model) {
    	FuncionarioDAO dao = new FuncionarioDAO();
    	if (funcionario.getCodCadastro() > 0) {
    		model.addAttribute("funcionario", dao.buscarFuncionario(funcionario.getCodCadastro()));
    		return "cadastroFuncionario";
    	}
    	return "cadastroFuncionario";
	}
    
    @RequestMapping("/adicionaFuncionario")
	public String adicionaFuncionario(@Valid Funcionario funcionario, BindingResult result, Model model, @RequestParam("dataNascimentoStr") String dataN) {
		FuncionarioDAO dao = new FuncionarioDAO();
    	Util util = new Util();
    	funcionario.setDataNascimento(util.transformaData(dataN));
    	
		if (temErro(funcionario) != "" || result.hasFieldErrors("nome, cpf, telefone, sexo, endereco, dataNascimentoStr, curso, email")) {
			System.out.println(temErro(funcionario));
			model.addAttribute("funcionario", funcionario);
			return "cadastroFuncionario";
		} else {
			if (funcionario.getCodCadastro() > 0) {
				dao.editarFuncionario(funcionario);
				return "redirect:listaFuncionarios";
			} else {
				dao.cadastrarFuncionario(funcionario);
				return "funcionario-adicionado";
			}
		}
	}
    
    @RequestMapping("/listaFuncionarios")
	public String listaFuncionarios(Model model) {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listarFuncionarios(null);
		model.addAttribute("funcionarios", funcionarios);
		return "listaFuncionario";
	}
    
    @RequestMapping("/removeFuncionario")
	public String remove(Funcionario funcionario, Model model) {
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.excluirFuncionario(funcionario);
		List<Funcionario> funcionarios = dao.listarFuncionarios(null);
		model.addAttribute("funcionarios", funcionarios);
		return "redirect:listaFuncionarios";
	}
    
    @RequestMapping("/filtroFuncionario")
	public String filtroFuncionario(String status, Model model) {
		FuncionarioDAO dao = new FuncionarioDAO();
    	Funcionario funcionario = new Funcionario();
    	if (!status.equalsIgnoreCase("TODOS")) {
        	funcionario.setStatus(status);
    		model.addAttribute("funcionarios", dao.listarFuncionarios(funcionario));
    	} else {
    		model.addAttribute("funcionarios", dao.listarFuncionarios(null));
    	}
		return "filtroFuncionario";
	}
    
    public String temErro(Funcionario funcionario) {
		Util util = new Util();
		String errorMsg = "";
		
		if(funcionario.getNome().isEmpty()) {
			errorMsg = errorMsg + "-Nome inválido ou não preenchido. \n";
		} if ((funcionario.getEndereco().isEmpty())) {
			errorMsg = errorMsg + "-Endereço inválido ou não preenchido. \n";
		} if ((funcionario.getSexo().isEmpty())) {
			errorMsg = errorMsg + "-Sexo inválido. \n";
		} if (funcionario.getCpf().isEmpty() || util.isCPF(funcionario.getCpf()) == false) {
			errorMsg = errorMsg + "-CPF inválido ou não preenchido. \n";
		} if (funcionario.getTelefone().isEmpty()) {
			errorMsg = errorMsg + "-Telefone inválido ou não preenchido. \n";
		} if (funcionario.getDataNascimentoStr() == null || util.validaData(funcionario.getDataNascimentoStr()) == false) {
			errorMsg = errorMsg + "-Data de nascimento inválida ou não preenchida. \n";
		} if (funcionario.getEmail().isEmpty()) {
			errorMsg = errorMsg + "-E-Mail inválido ou não preenchido. \n";
		} if (funcionario.getCargo().equals("Selecionar o Cargo..")) {
			errorMsg = errorMsg + "-Cargo inválido. \n";
		} if (funcionario.getCargo().equals("Professor")) {
			if (funcionario.getDisciplina().equals("Selecionar Disciplina..") || funcionario.getDisciplina() == null) {
				errorMsg = errorMsg + "-Disciplina inválida.";
			}
		} if (util.mascaraPagamento(funcionario.getSalarioStr()) <= 0) {
			errorMsg = errorMsg + "-Salário inválido ou zerado.";
		} if (util.mascaraPagamento(funcionario.getVAStr()) <= 0) {
			errorMsg = errorMsg + "-Vale alimentação inválido ou zerado.";
		} if (util.mascaraPagamento(funcionario.getVRStr()) <= 0) {
			errorMsg = errorMsg + "-Vale refeição inválido ou zerado.";
		} if (util.mascaraPagamento(funcionario.getVTStr()) <= 0) {
			errorMsg = errorMsg + "-Vale transporte inválido ou zerado.";
		} if (funcionario.getKids() < 0) {
			errorMsg = errorMsg + "-Número de dependentes inválido.";
		}
		return errorMsg;
	}
}

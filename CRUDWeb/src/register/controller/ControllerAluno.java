package register.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import register.dao.AlunoDAO;
import register.model.Aluno;

@Controller
public class ControllerAluno {    
    @RequestMapping("/cadastroAluno")
	public String form() {
    	return "cadastroAluno";
	}
    
    @RequestMapping("/adicionaAluno")
	public String adicionaAluno(@Valid Aluno aluno, BindingResult result, Model model) {
		AlunoDAO dao = new AlunoDAO();
		if (temErro(aluno).equals("") || result.hasFieldErrors("descricao")) {
			System.out.println(temErro(aluno));
			model.addAttribute("aluno", aluno);
			return "cadastroAluno";
		} else {
			if (aluno.getMatricula() > 0) {
				dao.editarAluno(aluno);
				return "redirect:listaAlunos";
			} else {
				dao.adicionarAluno(aluno);
				return "aluno-adicionado";
			}
		}
	}
    
    public String temErro(Aluno aluno) {
		Util util = new Util();
		String errorMsg = "";
		
		if(aluno.getNome().isEmpty()) {
			errorMsg = errorMsg + "-Nome inválido ou não preenchido. \n";
		} if ((aluno.getEndereco().isEmpty())) {
			errorMsg = errorMsg + "-Endereço inválido ou não preenchido. \n";
		} if ((aluno.getSexo().isEmpty())) {
			errorMsg = errorMsg + "-Sexo inválido. \n";
		} if (aluno.getCpf().isEmpty() || util.isCPF(aluno.getCpf()) == false) {
			errorMsg = errorMsg + "-CPF inválido ou não preenchido. \n";
		} if (aluno.getTelefone().isEmpty()) {
			errorMsg = errorMsg + "-Telefone inválido ou não preenchido. \n";
		} if (aluno.getDataNascimento() == null || util.validaData(aluno.getDataNascimento().toString()) == false) {
			errorMsg = errorMsg + "-Data de nascimento inválida ou não preenchida. \n";
		} if (aluno.getEmail().isEmpty()) {
			errorMsg = errorMsg + "-E-Mail inválido ou não preenchido. \n";
		}
		return errorMsg;
	}
}

package register.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import register.dao.AlunoDAO;
import register.model.Aluno;

@Controller
public class ControllerAluno {    
    @RequestMapping("/cadastroAluno")
	public String form(Aluno aluno, Model model) {
    	AlunoDAO dao = new AlunoDAO();
    	if (aluno.getMatricula() > 0) {
    		model.addAttribute("aluno", dao.buscarAluno(aluno.getMatricula()));
    		return "cadastroAluno";
    	}
    	return "cadastroAluno";
	}
    
    @RequestMapping("/adicionaAluno")
	public String adicionaAluno(@Valid Aluno aluno, BindingResult result, Model model, @RequestParam("dataNascimentoStr") String dataN) {
		AlunoDAO dao = new AlunoDAO();
    	Util util = new Util();
    	aluno.setDataNascimento(util.transformaData(dataN));
    	
		if (temErro(aluno) != "" || result.hasFieldErrors("nome, cpf, telefone, sexo, endereco, dataNascimentoStr, curso, email")) {
			System.out.println(temErro(aluno));
			model.addAttribute("aluno", aluno);
			return "cadastroAluno";
		} else {
			if (aluno.getMatricula() > 0) {
				dao.editarAluno(aluno);
				//aluno.setStatus(util.converteStatus(aluno.getStatus()));
				return "redirect:listaAlunos";
			} else {
				dao.adicionarAluno(aluno);
				return "aluno-adicionado";
			}
		}
	}
    
    @RequestMapping("/listaAlunos")
	public String listaAlunos(Model model) {
		AlunoDAO dao = new AlunoDAO();
		List<Aluno> alunos = dao.listarAlunos();
		model.addAttribute("alunos", alunos);
		return "listaAluno";
	}
    
    @RequestMapping("/removeAluno")
	public String remove(Aluno aluno, Model model) {
		AlunoDAO dao = new AlunoDAO();
		dao.excluirAluno(aluno);
		List<Aluno> alunos = dao.listarAlunos();
		model.addAttribute("alunos", alunos);
		return "redirect:listaAlunos";
	}
    
    @RequestMapping("/filtroTodos")
	public String filtroTodos(Model model) {
		AlunoDAO dao = new AlunoDAO();
		model.addAttribute("alunos", dao.listarAlunos());
		return "filtroTodos";
	}
    
    @RequestMapping("/filtroAtivos")
	public String filtroAtivo(Model model) {
		AlunoDAO dao = new AlunoDAO();
		model.addAttribute("alunos", dao.listarAlunosAtivos());
		return "filtroAtivo";
	}
    
    @RequestMapping("/filtroSuspensos")
	public String filtroSuspensos(Model model) {
		AlunoDAO dao = new AlunoDAO();
		model.addAttribute("alunos", dao.listarAlunosSuspensos());
		return "filtroSuspensos";
	}
    
    @RequestMapping("/filtroReprovados")
	public String filtroReprovados(Model model) {
		AlunoDAO dao = new AlunoDAO();
		model.addAttribute("alunos", dao.listarAlunosReprovados());
		return "filtroReprovados";
	}
    
    @RequestMapping("/filtroInativos")
	public String filtroInativos(Model model) {
		AlunoDAO dao = new AlunoDAO();
		model.addAttribute("alunos", dao.listarAlunosInativos());
		return "filtroInativos";
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
		} if (aluno.getDataNascimentoStr() == null || util.validaData(aluno.getDataNascimentoStr()) == false) {
			errorMsg = errorMsg + "-Data de nascimento inválida ou não preenchida. \n";
		} if (aluno.getEmail().isEmpty()) {
			errorMsg = errorMsg + "-E-Mail inválido ou não preenchido. \n";
		}
		return errorMsg;
	}
}

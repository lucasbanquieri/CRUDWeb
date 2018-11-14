package register.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("aluno");
    EntityManager manager = factory.createEntityManager();
	
    @RequestMapping("/cadastroAluno")
	public String form(Aluno aluno, Model model) {
    	if (aluno.getMatricula() > 0) {
    		model.addAttribute("aluno", manager.find(Aluno.class, aluno.getMatricula()));
    		manager.close();
	        factory.close();
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
		        manager.getTransaction().begin();  
			    manager.persist(aluno);
			    manager.getTransaction().commit();
			    
		        manager.close();
		        factory.close();
				return "aluno-adicionado";
			}
		}
	}
    
    @RequestMapping("/listaAlunos")
	public String listaAlunos(Model model) {
		Query query = manager.createQuery("SELECT a FROM Aluno as a where a.matricula > :matricula");
		query.setParameter("matricula", 0);
		List<Aluno> alunos = query.getResultList();
		manager.close();
	    factory.close();
		model.addAttribute("alunos", alunos);
		return "listaAluno";
	}
    
    @RequestMapping("/removeAluno")
	public String remove(Aluno aluno, Model model) {
		AlunoDAO dao = new AlunoDAO();
		aluno.setStatus("INATIVO");
		manager.getTransaction().begin(); 
		manager.merge(aluno);
		Query querySelect = manager.createQuery("SELECT a FROM Aluno as a where a.matricula > :matricula");
		querySelect.setParameter("matricula", 0);
		manager.getTransaction().commit();
		List<Aluno> alunos = querySelect.getResultList();
		model.addAttribute("alunos", alunos);
		return "redirect:listaAlunos";
	}
    
    @RequestMapping("/filtroAluno")
	public String filtroTodos(String status, Model model) {
		AlunoDAO dao = new AlunoDAO();
    	Aluno aluno = new Aluno();
    	if (!status.equalsIgnoreCase("TODOS")) {
        	aluno.setStatus(status);
    		model.addAttribute("alunos", dao.listarAlunos(aluno));
    	} else {
    		model.addAttribute("alunos", dao.listarAlunos(null));
    	}
		return "filtroAluno";
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

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

import register.model.Aluno;

@Controller
public class ControllerAluno {
	
    @RequestMapping("/cadastroAluno")
	public String form(Aluno aluno, Model model) {
    	if (aluno.getMatricula() > 0) {
    		EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
    	    EntityManager manager = factory.createEntityManager();
    		model.addAttribute("aluno", manager.find(Aluno.class, aluno.getMatricula()));
    		manager.close();
	        factory.close();
    		return "cadastroAluno";
    	}
    	return "cadastroAluno";
	}
    
    @RequestMapping("/adicionaAluno")
	public String adicionaAluno(@Valid Aluno aluno, BindingResult result, Model model, @RequestParam("dataNascimentoStr") String dataN) {
    	Util util = new Util();
    	aluno.setDataNascimento(util.transformaData(dataN));
    	
		if (temErro(aluno) != "" || result.hasFieldErrors("nome, cpf, telefone, sexo, endereco, dataNascimentoStr, curso, email")) {
			System.out.println(temErro(aluno));
			model.addAttribute("aluno", aluno);
			return "cadastroAluno";
		} else {
			if (aluno.getMatricula() > 0) {
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
			    EntityManager manager = factory.createEntityManager();
				manager.getTransaction().begin();
				manager.merge(aluno);
				manager.getTransaction().commit();
				
				manager.close();
		        factory.close();
				return "redirect:listaAlunos";
			} else {
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
			    EntityManager manager = factory.createEntityManager();
		        manager.getTransaction().begin();  
			    manager.persist(aluno);
			    manager.getTransaction().commit();
			    
		        manager.close();
		        factory.close();
				return "aluno-adicionado";
			}
		}
	}
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/listaAlunos")
	public String listaAlunos(Model model) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
        EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("SELECT a FROM Aluno as a where a.matricula > :matricula");
		query.setParameter("matricula", 0);
		List<Aluno> alunos = query.getResultList();
		for (Aluno aluno : alunos) {
			if (aluno.getStatus().length() == 1) {
				aluno.setStatus(aluno.getStatus());
			}
		}
		manager.close();
        factory.close();
		model.addAttribute("alunos", alunos);
		return "listaAluno";
	}
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/removeAluno")
	public String remove(Aluno aluno, Model model) {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
        EntityManager manager = factory.createEntityManager();
		Aluno update = manager.find(Aluno.class, aluno.getMatricula());
		update.setMatricula(update.getMatricula());
		update.setStatus("6");
		manager.getTransaction().begin();
		manager.merge(update);
		manager.getTransaction().commit();
		Query querySelect = manager.createQuery("SELECT a FROM Aluno as a where a.matricula > :matricula");
		querySelect.setParameter("matricula", 0);
		List<Aluno> alunos = querySelect.getResultList();
		for (Aluno alu : alunos) {
			if (alu.getStatus().length() == 1) {
				alu.setStatus(alu.getStatus());
			}
		}
		manager.close();
        factory.close();
		model.addAttribute("alunos", alunos);
		return "redirect:listaAlunos";
	}
    
    @SuppressWarnings("unchecked")
	@RequestMapping("/filtroAluno")
	public String filtroTodos(String status, Model model) {
    	if (!status.equalsIgnoreCase("TODOS")) {
    		EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
            EntityManager manager = factory.createEntityManager();
    		Query query = manager.createQuery("SELECT a FROM Aluno as a WHERE a.status = :status");
    		query.setParameter("status", status);
    		List<Aluno> alunos = query.getResultList();
    		for (Aluno alu : alunos) {
    			if (alu.getStatus().length() == 1) {
    				alu.setStatus(alu.getStatus());
    			}
    		}
    		manager.close();
            factory.close();
            model.addAttribute("alunos", alunos);
    	} else {
    		EntityManagerFactory factory = Persistence.createEntityManagerFactory("cadastro");
            EntityManager manager = factory.createEntityManager();
    		Query query = manager.createQuery("SELECT a FROM Aluno as a WHERE a.matricula > :matricula");
    		query.setParameter("matricula", 0);
    		List<Aluno> alunos = query.getResultList();
    		for (Aluno alu : alunos) {
    			if (alu.getStatus().length() == 1) {
    				alu.setStatus(alu.getStatus());
    			}
    		}
    		manager.close();
            factory.close();
    		model.addAttribute("alunos", alunos);
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

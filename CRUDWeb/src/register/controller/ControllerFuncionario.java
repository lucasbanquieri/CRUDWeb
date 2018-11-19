package register.controller;

import java.util.ArrayList;
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

import register.dao.FuncionarioDAO;
import register.model.Funcionario;
import register.model.Kid;

@Controller
public class ControllerFuncionario {
    @SuppressWarnings("unchecked")
	@RequestMapping("/cadastroFuncionario")
	public String form(Funcionario funcionario, Model model) {
    	if (funcionario.getCodCadastro() > 0) {
    		EntityManagerFactory factory = Persistence.createEntityManagerFactory("aluno");
    	    EntityManager manager = factory.createEntityManager();
    		model.addAttribute("funcionario", manager.find(Funcionario.class, funcionario.getCodCadastro()));
    		Query query = manager.createQuery("SELECT k FROM Kid as k where k.fk_cod_cadastro = :codCadastro");
    		query.setParameter("codCadastro", funcionario.getCodCadastro());
    		List<Kid> kids = query.getResultList();
    		manager.close();
            factory.close();
    		model.addAttribute("kid", kids);
    		return "cadastroFuncionario";
    	}
    	return "cadastroFuncionario";
	}
    
    @RequestMapping("/adicionaFuncionario")
	public String adicionaFuncionario(@Valid Funcionario funcionario, BindingResult result, Model model, @RequestParam("dataNascimentoStr") String dataN, String[] nomeK, String[] dataNK) {
    	Util util = new Util();
    	funcionario.setDataNascimento(util.transformaData(dataN));
    	List<Kid> listKids = new ArrayList<Kid>();
    	//ADICIONA KIDS NO ARRAYLIST
    	for (int i=0; i < nomeK.length; i++) {
    		if (!nomeK[i].isEmpty() && util.validaData(dataNK[i])) {
    			Kid kid = new Kid();
        		kid.setNome(nomeK[i]);
        		kid.setDataNascimentoStr(dataNK[i]);
        		kid.setDataNascimento(util.transformaData(kid.getDataNascimentoStr()));
        		listKids.add(kid);
    		} else {
    			System.out.println("Dados do dependente inválido.");
    		}
    	}
    	funcionario.setArrayKids(listKids);
    	//VERIFICA ERROS NOS DADOS DO FUNCIONARIO
		if (temErro(funcionario) != "" || result.hasFieldErrors("nome, cpf, telefone, sexo, endereco, dataNascimentoStr, curso, email")) {
			System.out.println(temErro(funcionario));
			model.addAttribute("funcionario", funcionario);
			return "cadastroFuncionario";
		} else {
			if (funcionario.getCodCadastro() > 0) {
				//EDITAR FUNCIONARIO
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("aluno");
			    EntityManager manager = factory.createEntityManager();
				manager.getTransaction().begin();
				manager.merge(funcionario);
				manager.getTransaction().commit();
				
				manager.close();
		        factory.close();
				return "redirect:listaFuncionarios";
			} else {
				//NOVO FUNCIONARIO
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("aluno");
			    EntityManager manager = factory.createEntityManager();
		        manager.getTransaction().begin();
			    manager.persist(funcionario);
			    manager.getTransaction().commit();
			    
		        manager.close();
		        factory.close();
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
        	//funcionario.setStatus(status);
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

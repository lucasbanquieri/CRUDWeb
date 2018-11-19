package register.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import register.model.Aluno;

public class GenerateTables {
	public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("aluno");
        EntityManager manager = factory.createEntityManager();
        Aluno aluno = new Aluno();
        aluno.setNome("JPA 2");
        aluno.setCpf("109.674.496-45");
        Date dataNascimento = new Date();
        aluno.setDataNascimento(dataNascimento);
        aluno.setEmail("jpa@hibernate.org");
        aluno.setEndereco("Rua Teste 101");
        aluno.setSexo("M");
        aluno.setTelefone("(43)3337-4591");
        //aluno.setStatus("ATIVO");
        aluno.setCurso("Java Web");
        
		manager.getTransaction().begin();  
	    manager.persist(aluno);
	    manager.getTransaction().commit();
	    
        manager.close();
        factory.close();
	}
}

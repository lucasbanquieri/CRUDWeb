package register.controller;

import java.util.ArrayList;
import java.util.List;

import register.dao.AlunoDAO;
import register.model.Aluno;

public class ControllerAluno {
    public static ArrayList<Aluno> arrayAluno;
    
    public ControllerAluno() {
    	if (arrayAluno == null) {
            arrayAluno = new ArrayList<Aluno>();
    	}
    }
    
	public void cadastrarAluno(Aluno aluno) {
		arrayAluno.add(aluno);
		AlunoDAO alunoDao = new AlunoDAO();
		alunoDao.cadastrarAluno(aluno);
	}
	
	public List<Aluno> listarAlunos() {
		AlunoDAO alunoDao = new AlunoDAO();
		return alunoDao.listarAlunos();
	}
	
	public void excluirAluno(Aluno aluno) {
		AlunoDAO alunoDao = new AlunoDAO();
		alunoDao.excluirAluno(aluno);
	}
	
	public void editarAluno(Aluno aluno) {
		AlunoDAO alunoDao = new AlunoDAO();
		alunoDao.editarAluno(aluno);
	}
}

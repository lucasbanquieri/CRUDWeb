package register.controller;

import java.util.ArrayList;
import java.util.List;

import register.dao.FuncionarioDAO;
import register.model.Funcionario;
import register.model.Kid;

public class Controller{
    
    public static ArrayList<Funcionario> arrayFunc;

    public Controller() {
    	if (arrayFunc == null) {
            arrayFunc = new ArrayList<Funcionario>();
    	}
    }
        
    public void cadastrarProfessor(Funcionario funcionario) {
		arrayFunc.add(funcionario);
		FuncionarioDAO funcDao = new FuncionarioDAO();
		funcDao.cadastrarFuncionario(funcionario);
	}
	
	public void cadastrarFuncionario(Funcionario funcionario) {
		arrayFunc.add(funcionario);
		FuncionarioDAO funcDao = new FuncionarioDAO();
		funcDao.cadastrarFuncionario(funcionario);
	}
	
	public List<Funcionario> listarFuncionarios() {
		FuncionarioDAO funcDao = new FuncionarioDAO();
		return funcDao.listarFuncionarios();
	}
	
	public void excluirFuncionario(Funcionario funcionario) {
		FuncionarioDAO funcDao = new FuncionarioDAO();
		funcDao.excluirFuncionario(funcionario);
	}
	
	public void editarFuncionario(Funcionario funcionario) {
		FuncionarioDAO funcDao = new FuncionarioDAO();
		funcDao.editarFuncionario(funcionario);
	}
	
	public List<Kid> listarKids(Funcionario funcionario) {
		FuncionarioDAO funcDao = new FuncionarioDAO();
		return funcDao.listarKids(funcionario);
	}
	
	public void excluirKid(Kid kid) {
		FuncionarioDAO funcDao = new FuncionarioDAO();
		funcDao.excluirKid(kid);
	}
	
	public void editarKid(Kid kid) {
		FuncionarioDAO funcDao = new FuncionarioDAO();
		funcDao.editarKid(kid);
	}
}

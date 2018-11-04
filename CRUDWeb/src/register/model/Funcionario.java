package register.model;

import java.util.Date;
import java.util.List;

import register.controller.Util;

public class Funcionario extends Pessoa {
	private double salario;
	private String salarioStr;
	private double VA;
	private String VAStr;
	private double VR;
	private String VRStr;
	private double VT;
	private String VTStr;
	private int Kids;
	private String cargo;
	private int codCadastro;
	private String disciplina;
	private List<Kid> arrayKids;
	
	public Funcionario(String cpf, String telefone, String nome, Date dataNascimento, String sexo, String endereco,
			String email, double salario, double vA, double vR, double vT, int kids, String cargo, String disciplina) {
		super(cpf, telefone, nome, dataNascimento, sexo, endereco, email);
		this.salario = salario;
		VA = vA;
		VR = vR;
		VT = vT;
		Kids = kids;
		this.cargo = cargo;
		this.disciplina = disciplina;
	}
	
	public Funcionario() {
		super();
	}
	
	public String getVAStr() {
		return VAStr;
	}

	public void setVAStr(String vAStr) {
		VAStr = vAStr;
	}

	public String getVRStr() {
		return VRStr;
	}

	public void setVRStr(String vRStr) {
		VRStr = vRStr;
	}

	public String getVTStr() {
		return VTStr;
	}

	public void setVTStr(String vTStr) {
		VTStr = vTStr;
	}

	public String getSalarioStr() {
		return salarioStr;
	}

	public void setSalarioStr(String salarioStr) {
		this.salarioStr = salarioStr;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		Util util = new Util();
		this.salario = util.mascaraPagamento(getSalarioStr());
	}

	public double getVA() {
		return VA;
	}

	public void setVA(double vA) {
		Util util = new Util();
		this.VA = util.mascaraPagamento(getVAStr());
	}

	public double getVR() {
		return VR;
	}

	public void setVR(double vR) {
		Util util = new Util();
		this.VR = util.mascaraPagamento(getVRStr());
	}

	public double getVT() {
		return VT;
	}

	public void setVT(double vT) {
		Util util = new Util();
		this.VT = util.mascaraPagamento(getVTStr());
	}

	public int getKids() {
		return Kids;
	}

	public void setKids(int kids) {
		Kids = kids;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getCodCadastro() {
		return codCadastro;
	}

	public void setCodCadastro(int codCadastro) {
		this.codCadastro = codCadastro;
	}

	public List<Kid> getArrayKids() {
		return arrayKids;
	}

	public void setArrayKids(List<Kid> arrayKids) {
		this.arrayKids = arrayKids;
	}
	
}
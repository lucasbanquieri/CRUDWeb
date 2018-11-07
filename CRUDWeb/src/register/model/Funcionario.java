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
		Util util = new Util();
		return util.doubleToString(this.VA);
	}

	public void setVAStr(String vAStr) {
		Util util = new Util();
		this.VA = util.mascaraPagamento(vAStr);
		VAStr = vAStr;
	}

	public String getVRStr() {
		Util util = new Util();
		return util.doubleToString(this.VR);
	}

	public void setVRStr(String vRStr) {
		Util util = new Util();
		this.VR = util.mascaraPagamento(vRStr);
		VRStr = vRStr;
	}

	public String getVTStr() {
		Util util = new Util();
		return util.doubleToString(this.VT);
	}

	public void setVTStr(String vTStr) {
		Util util = new Util();
		this.VT = util.mascaraPagamento(vTStr);
		VTStr = vTStr;
	}

	public String getSalarioStr() {
		Util util = new Util();
		return util.doubleToString(this.salario);
	}

	public void setSalarioStr(String salarioStr) {
		Util util = new Util();
		this.salario = util.mascaraPagamento(salarioStr);
		this.salarioStr = salarioStr;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public double getSalario() {
		Util util = new Util();
		if (salarioStr != null) {
			return util.mascaraPagamento(getSalarioStr());
		}
		return this.salario;
	}

	public void setSalario(double salario) {
		Util util = new Util();
		if (salarioStr != null) {
			salario = util.mascaraPagamento(salarioStr);
		} else {
			this.salario = salario;
		}
	}

	public double getVA() {
		Util util = new Util();
		if (VAStr != null) {
			return util.mascaraPagamento(VAStr);
		}
		return this.VA;
	}

	public void setVA(double vA) {
		Util util = new Util();
		if (VAStr != null) {
			vA = util.mascaraPagamento(VAStr);
		} else {
			this.VA = vA;
		}
	}

	public double getVR() {
		Util util = new Util();
		if (VRStr != null) {
			return util.mascaraPagamento(VRStr);
		}
		return this.VR;
	}

	public void setVR(double vR) {
		Util util = new Util();
		if (VAStr != null) {
			vR = util.mascaraPagamento(VRStr);
		} else {
			this.VR = vR;
		}
	}

	public double getVT() {
		Util util = new Util();
		if (VTStr != null) {
			return util.mascaraPagamento(VTStr);
		}
		return this.VT;
	}

	public void setVT(double vT) {
		Util util = new Util();
		if (VTStr != null) {
			vT = util.mascaraPagamento(VTStr);
		} else {
			this.VT = vT;
		}
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
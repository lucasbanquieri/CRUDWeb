package register.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import register.controller.Util;

@MappedSuperclass
public class Pessoa {
	private String cpf;
	private String telefone;
	private String nome;
	@Column(name="data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Transient
	private String dataNascimentoStr;
	private String sexo;
	private String endereco;
	private String email;
	private String status;
	
	public Pessoa(String cpf, String telefone, String nome, Date dataNascimento, String sexo, String endereco, String email) {
		super();
		this.cpf = cpf;
		this.telefone = telefone;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.endereco = endereco;
		this.email = email;
	}
	
	public Pessoa() {
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		//Util util = new Util();
		this.status = status;
	}

	public String getDataNascimentoStr() {
		return dataNascimentoStr;
	}

	public void setDataNascimentoStr(String dataNascimentoStr) {
		this.dataNascimentoStr = dataNascimentoStr;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		Util util = new Util();
		this.telefone = util.mascaraTelefone(telefone);
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		Util util = new Util();
		this.cpf = util.mascaraCpf(cpf);
	}
	public Date getDataNascimento() {
		Util util = new Util();
		if (getDataNascimentoStr() != null) {
			dataNascimento = util.transformaData(getDataNascimentoStr());
			return dataNascimento;
		} else {
			return dataNascimento;
		}
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
}

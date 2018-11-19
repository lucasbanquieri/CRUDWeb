package register.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import register.controller.Util;

@Entity
@Table(name="kid")
public class Kid {
	private String nome;
	private Date dataNascimento;
	private String dataNascimentoStr;
	private int fkCodCadastro;
	@Id
	@GeneratedValue
	private int kidId;
	
	public Kid(String nome, Date dataNascimento) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public Kid() {
		super();
	}
	
	public String getDataNascimentoStr() {
		return dataNascimentoStr;
	}

	public void setDataNascimentoStr(String dataNascimentoStr) {
		this.dataNascimentoStr = dataNascimentoStr;
	}

	public int getKidId() {
		return kidId;
	}

	public void setKidId(int kidId) {
		this.kidId = kidId;
	}

	public int getFkCodCadastro() {
		return fkCodCadastro;
	}

	public void setFkCodCadastro(int fkCodCadastro) {
		this.fkCodCadastro = fkCodCadastro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	@Override
	public String toString() {
		return "Kid [nome=" + nome + ", dataNascimento=" + dataNascimento + "]";
	}
}
package register.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import register.controller.Util;

@Entity
@Table(name="filhos")
public class Kid {
	private String nome;
	@Column(name="data_nascimento")
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;
	@Transient
	private String dataNascimentoStr;
	@Transient
	private int fk_cod_cadastro;
	@ManyToOne
	@JoinColumn(name="fk_cod_cadastro")
	private Funcionario funcionario;
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
		return fk_cod_cadastro;
	}

	public void setFkCodCadastro(int fkCodCadastro) {
		this.fk_cod_cadastro = fkCodCadastro;
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
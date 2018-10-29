package register.model;

import java.util.Date;

public class Kid {
	private String nome;
	private Date dataNascimento;
	private int fkCodCadastro;
	private int kidId;
	
	public Kid(String nome, Date dataNascimento) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
	
	public Kid() {
		super();
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
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Kid [nome=" + nome + ", dataNascimento=" + dataNascimento + "]";
	}
}
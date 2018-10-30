package register.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Pessoa {
	@NotNull
	@Size(min=11, max=11)
	private String cpf;
	@NotNull
	@Size(max=11)
	private String telefone;
	@NotNull
	@Size(max=50)
	private String nome;
	@NotNull
	@Past
	private Date dataNascimento;
	private String sexo;
	@NotNull
	@Size(max=50)
	private String endereco;
	@NotNull
	@Size(max=30)
	private String email;	
	
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

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
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

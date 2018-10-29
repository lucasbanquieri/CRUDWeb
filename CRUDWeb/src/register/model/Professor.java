package register.model;

import java.util.Date;

public class Professor extends Pessoa {
	private String disciplina;

	public Professor(String cpf, String telefone, String nome, Date dataNascimento, String sexo, String endereco,
			String email, String disciplina) {
		super(cpf, telefone, nome, dataNascimento, sexo, endereco, email);
		this.disciplina = disciplina;
	}

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
}

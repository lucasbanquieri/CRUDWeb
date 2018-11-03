package register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import register.model.Aluno;

public class AlunoDAO {
	BancoDados db = null;
	
	public AlunoDAO() {
		try {
			db = new BancoDados("curso_javaweb");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	public void adicionarAluno(Aluno aluno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO  aluno (cpf, telefone, nome, data_nascimento, sexo, endereco, email, curso, status)");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, aluno.getCpf());
			stmt.setString(2, aluno.getTelefone());
			stmt.setString(3, aluno.getNome());
			stmt.setDate(4, d);
			stmt.setString(5, aluno.getSexo());
			stmt.setString(6, aluno.getEndereco());
			stmt.setString(7, aluno.getEmail());
			stmt.setString(8, aluno.getCurso());
			stmt.setString(9, "1");

			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método cadastrarAluno - rollback");
				}
			}
			System.out.println("Erro no método cadastrarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
	
	public List<Aluno> listarAlunos() {
		ArrayList<Aluno> arrayAluno = new ArrayList<Aluno>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT cpf, telefone, nome, data_nascimento, sexo, endereco, email, matricula, curso, status"
					+ " FROM  aluno"
					+ " WHERE matricula > 0"
					+ " ORDER BY matricula ASC";

			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Aluno aluno = new Aluno();
				
				aluno.setCpf(rs.getString(1));
				aluno.setTelefone(rs.getString(2));
				aluno.setNome(rs.getString(3));
				aluno.setDataNascimento(new Date(rs.getTimestamp("data_nascimento").getTime()));
				aluno.setSexo(rs.getString(5));
				aluno.setEndereco(rs.getString(6));
				aluno.setEmail(rs.getString(7));
				aluno.setMatricula(rs.getInt(8));
				aluno.setCurso(rs.getString(9));
				aluno.setStatus(rs.getString(10));
				
				arrayAluno.add(aluno);
			}

		} catch (SQLException e) {
			System.out.println("Erro no método lerAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		return arrayAluno;
	}
	
	public void editarAluno(Aluno aluno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE aluno SET telefone = ?, nome = ?, data_nascimento = ?, sexo = ?, endereco = ?, email = ?, curso = ?, status = ? ");
			sql.append("WHERE matricula = ?;");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, aluno.getTelefone());
			stmt.setString(2, aluno.getNome());
			stmt.setDate(3, d);
			stmt.setString(4, aluno.getSexo());
			stmt.setString(5, aluno.getEndereco());
			stmt.setString(6, aluno.getEmail());
			stmt.setString(7, aluno.getCurso());
			stmt.setString(8, aluno.getStatus());
			stmt.setInt(9, aluno.getMatricula());

			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método editarAluno - rollback");
				}
			}
			System.out.println("Erro no método editarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
	
	public void excluirAluno(Aluno aluno) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			//java.sql.Date d = new java.sql.Date(aluno.getDataNascimento().getTime());

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE aluno SET status = ? ");
			sql.append("WHERE matricula = ?;");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, "4");
			stmt.setInt(2, aluno.getMatricula());

			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método excluirAluno - rollback");
				}
			}
			System.out.println("Erro no método excluirAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
	
	public Aluno buscarAluno(int matricula) {
		Aluno aluno = new Aluno();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT cpf, telefone, nome, data_nascimento, sexo, endereco, email, matricula, curso, status"
					+ " FROM  aluno"
					+ " WHERE matricula = ?";

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setLong(1, matricula);

			rs = stmt.executeQuery();

			while (rs.next()) {
				aluno.setCpf(rs.getString(1));
				aluno.setTelefone(rs.getString(2));
				aluno.setNome(rs.getString(3));
				aluno.setDataNascimento(new Date(rs.getTimestamp("data_nascimento").getTime()));
				aluno.setSexo(rs.getString(5));
				aluno.setEndereco(rs.getString(6));
				aluno.setEmail(rs.getString(7));
				aluno.setMatricula(rs.getInt(8));
				aluno.setCurso(rs.getString(9));
				aluno.setStatus(rs.getString(10));
			}

		} catch (SQLException e) {
			System.out.println("Erro no método buscarAluno");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		return aluno;
	}
}

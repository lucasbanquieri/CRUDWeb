package register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import register.model.Funcionario;
import register.model.Kid;

public class FuncionarioDAO {
	BancoDados db = null;
	
	public FuncionarioDAO() {
		try {
			db = new BancoDados("curso_javaweb");
		} catch (NamingException e) {
			System.out.println("Erro ao instanciar o Banco de Dados: " + e);
		}
	}
	
	public void cadastrarFuncionario(Funcionario func) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			java.sql.Date d = new java.sql.Date(func.getDataNascimento().getTime());

			StringBuffer sql = new StringBuffer();
			
			sql.append("INSERT INTO funcionario (telefone, nome, data_nascimento, sexo, endereco, email, salario, vale_alimentacao, vale_refeicao, vale_transporte, kids, cargo, cod_cadastro, disciplina, cpf, ativo) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, func.getTelefone());
			stmt.setString(2, func.getNome());
			stmt.setDate(3, d);
			stmt.setString(4, func.getSexo());
			stmt.setString(5, func.getEndereco());
			stmt.setString(6, func.getEmail());
			stmt.setDouble(7, func.getSalario());
			stmt.setDouble(8, func.getVA());
			stmt.setDouble(9, func.getVR());
			stmt.setDouble(10, func.getVT());
			stmt.setInt(11, func.getKids());
			stmt.setString(12, func.getCargo());
			stmt.setInt(13, func.getCodCadastro());
			if (!func.getDisciplina().equals("")) {
				stmt.setString(14, func.getDisciplina());
			} else {
				stmt.setNull(14, Types.NULL);
			}
			stmt.setString(15, func.getCpf());
			stmt.setInt(16, 1);

			stmt.execute();
			conn.commit();
			
			String sqlLastInsert = "SELECT LAST_INSERT_ID()";

			db.finalizaObjetos(null, stmt, null);
			stmt = conn.prepareStatement(sqlLastInsert);
			rs = stmt.executeQuery();

			if (rs.next()) {
				func.setCodCadastro(rs.getInt(1));
				cadastrarKids(func);
				db.finalizaObjetos(null, stmt, null);
			}
			
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método cadastrarFuncionario - rollback");
				}
			}
			System.out.println("Erro no método cadastrarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		
		System.out.println("Funcionario " + func.getNome() + " no cargo de " + func.getCargo() + " cadastrado com sucesso!");
	}
	
	private void cadastrarKids(Funcionario funcionario) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			String sql = "INSERT INTO filhos (nome, data_nascimento, fk_cod_cadastro) VALUES (?, ?, ?); ";
			
			stmt = conn.prepareStatement(sql);

			for (Kid kid : funcionario.getArrayKids()) {
				java.sql.Date d = new java.sql.Date(kid.getDataNascimento().getTime());
				stmt.setString(1, kid.getNome());
				stmt.setDate(2, d);
				stmt.setInt(3, Integer.valueOf(funcionario.getCodCadastro()));

				stmt.addBatch();
			}

			stmt.executeBatch();

			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método cadastrarFuncionario - rollback");
				}
			}
			System.out.println("Erro no método cadastrarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
	
	public List<Funcionario> listarFuncionarios() {
		ArrayList<Funcionario> arrayFunc = new ArrayList<Funcionario>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT telefone, nome, data_nascimento, sexo, endereco, email, salario, vale_alimentacao, vale_refeicao, vale_transporte, kids, cargo, cod_cadastro, disciplina, cpf"
					+ " FROM  funcionario"
					+ " WHERE ativo = 1"
					+ " ORDER BY cod_cadastro ASC";

			stmt = conn.prepareStatement(sql.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Funcionario func = new Funcionario();
				
				func.setTelefone(rs.getString(1));
				func.setNome(rs.getString(2));
				func.setDataNascimento(new Date(rs.getTimestamp("data_nascimento").getTime()));
				func.setSexo(rs.getString(4));
				func.setEndereco(rs.getString(5));
				func.setEmail(rs.getString(6));
				func.setSalario(rs.getDouble(7));
				func.setVA(rs.getDouble(8));
				func.setVR(rs.getDouble(9));
				func.setVT(rs.getDouble(10));
				func.setKids(rs.getInt(11));
				func.setCargo(rs.getString(12));
				func.setCodCadastro(rs.getInt(13));
				func.setDisciplina(rs.getString(14));
				func.setCpf(rs.getString(15));
				
				arrayFunc.add(func);
			}

		} catch (SQLException e) {
			System.out.println("Erro no método listarFuncionarios");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		return arrayFunc;
	}
	
	public List<Kid> listarKids(Funcionario funcionario) {
		List<Kid> arrayKids = new ArrayList<Kid>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT nome, data_nascimento, kid_id"
					+ " FROM  filhos"
					+ " WHERE fk_cod_cadastro = ?"
					+ " ORDER BY fk_cod_cadastro ASC";

			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, funcionario.getCodCadastro());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Kid kid = new Kid();
				
				kid.setNome(rs.getString(1));
				kid.setDataNascimento(new Date(rs.getTimestamp("data_nascimento").getTime()));
				kid.setKidId(rs.getInt(3));
				
				arrayKids.add(kid);
			}

		} catch (SQLException e) {
			System.out.println("Erro no método listarKids");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		return arrayKids;
	}
	
	public void editarFuncionario(Funcionario func) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			java.sql.Date d = new java.sql.Date(func.getDataNascimento().getTime());

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE funcionario SET telefone = ?, nome = ?, data_nascimento = ?, sexo = ?, endereco = ?, email = ?, salario = ?, vale_alimentacao = ?, vale_refeicao = ?, vale_transporte = ?, kids = ?, cargo = ?, disciplina = ? ");
			sql.append("WHERE cod_cadastro = ?;");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, func.getTelefone());
			stmt.setString(2, func.getNome());
			stmt.setDate(3, d);
			stmt.setString(4, func.getSexo());
			stmt.setString(5, func.getEndereco());
			stmt.setString(6, func.getEmail());
			stmt.setDouble(7, func.getSalario());
			stmt.setDouble(8, func.getVA());
			stmt.setDouble(9, func.getVR());
			stmt.setDouble(10, func.getVT());
			stmt.setInt(11, func.getKids());
			stmt.setString(12, func.getCargo());
			stmt.setString(13, func.getDisciplina());
			stmt.setInt(14, Integer.valueOf(func.getCodCadastro()));

			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método editarFuncionario - rollback");
				}
			}
			System.out.println("Erro no método editarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
	
	public void excluirFuncionario(Funcionario func) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE funcionario SET ativo = ? ");
			sql.append("WHERE cod_cadastro = ?;");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setInt(1, 0);
			stmt.setInt(2, Integer.valueOf(func.getCodCadastro()));

			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método excluirFuncionario - rollback");
				}
			}
			System.out.println("Erro no método excluirFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
	
	public void excluirKid(Kid kid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);

			StringBuffer sql = new StringBuffer();
			
			sql.append("DELETE FROM filhos ");
			sql.append("WHERE kid_id = ?;");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setInt(1, Integer.valueOf(kid.getKidId()));

			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método excluirKid - rollback");
				}
			}
			System.out.println("Erro no método excluirKid");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
	
	public void editarKid(Kid kid) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			conn.setAutoCommit(false);
			java.sql.Date d = new java.sql.Date(kid.getDataNascimento().getTime());

			StringBuffer sql = new StringBuffer();
			
			sql.append("UPDATE filhos SET nome = ?, data_nascimento = ? ");
			sql.append("WHERE kid_id = ?;");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, kid.getNome());
			stmt.setDate(2, d);
			stmt.setInt(3, Integer.valueOf(kid.getKidId()));

			stmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("Erro no método editarKid - rollback");
				}
			}
			System.out.println("Erro no método editarKid");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
	}
}
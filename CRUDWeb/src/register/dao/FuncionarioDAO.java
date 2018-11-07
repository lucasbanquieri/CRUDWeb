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
			
			sql.append("INSERT INTO funcionario (telefone, nome, data_nascimento, sexo, endereco, email, salario, vale_alimentacao, vale_refeicao, vale_transporte, kids, cargo, disciplina, cpf, status) ");
			sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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
			if (!func.getDisciplina().equals("")) {
				stmt.setString(13, func.getDisciplina());
			} else {
				stmt.setNull(13, Types.NULL);
			}
			stmt.setString(14, func.getCpf());
			stmt.setString(15, "1");

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
	
	public List<Funcionario> listarFuncionarios(Funcionario filtroFuncionario) {
		ArrayList<Funcionario> arrayFunc = new ArrayList<Funcionario>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();
			
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT telefone, nome, data_nascimento, sexo, endereco, email, salario, vale_alimentacao, vale_refeicao, vale_transporte, kids, cargo, cod_cadastro, disciplina, cpf, status");
			sql.append(" FROM  funcionario");
			sql.append(" WHERE cod_cadastro > 0");
			
			if(filtroFuncionario != null && filtroFuncionario.getStatus() != null && !filtroFuncionario.getStatus().equals("")) {
				sql.append(" AND status = ?");
			}
			
			sql.append(" ORDER BY cod_cadastro ASC");

			stmt = conn.prepareStatement(sql.toString());
			
			if(filtroFuncionario != null && filtroFuncionario.getStatus() != null && !filtroFuncionario.getStatus().equals("")) {
				stmt.setString(1, filtroFuncionario.getStatus());
			}
			
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
				func.setStatus(rs.getString(16));
				
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
			
			sql.append("UPDATE funcionario SET telefone = ?, nome = ?, data_nascimento = ?, sexo = ?, endereco = ?, email = ?, salario = ?, vale_alimentacao = ?, vale_refeicao = ?, vale_transporte = ?, kids = ?, cargo = ?, disciplina = ?, status = ? ");
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
			stmt.setInt(15, Integer.valueOf(func.getCodCadastro()));
			stmt.setString(14, func.getStatus());

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
			
			sql.append("UPDATE funcionario SET status = ? ");
			sql.append("WHERE cod_cadastro = ?;");

			stmt = conn.prepareStatement(sql.toString());

			stmt.setString(1, "6");
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
	
	public Funcionario buscarFuncionario(int cod) {
		Funcionario funcionario = new Funcionario();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.obterConexao();

			String sql = "SELECT cpf, telefone, nome, data_nascimento, sexo, endereco, email, cod_cadastro, cargo, status, salario, vale_alimentacao, vale_refeicao, vale_transporte, kids, disciplina, status"
					+ " FROM  funcionario"
					+ " WHERE cod_cadastro = ?";

			stmt = conn.prepareStatement(sql.toString());
			
			stmt.setLong(1, cod);

			rs = stmt.executeQuery();

			while (rs.next()) {
				funcionario.setCpf(rs.getString(1));
				funcionario.setTelefone(rs.getString(2));
				funcionario.setNome(rs.getString(3));
				funcionario.setDataNascimento(new Date(rs.getTimestamp("data_nascimento").getTime()));
				funcionario.setSexo(rs.getString(5));
				funcionario.setEndereco(rs.getString(6));
				funcionario.setEmail(rs.getString(7));
				funcionario.setCodCadastro(rs.getInt(8));
				funcionario.setCargo(rs.getString(9));
				funcionario.setStatus(rs.getString(10));
				funcionario.setSalario(rs.getDouble(11));
				funcionario.setVA(rs.getDouble(12));
				funcionario.setVR(rs.getDouble(13));
				funcionario.setVT(rs.getDouble(14));
				funcionario.setKids(rs.getInt(15));
				funcionario.setStatus(rs.getString(17));
				if (funcionario.getCargo().equals("Professor")) {
					funcionario.setDisciplina(rs.getString(16));
				} else {
					funcionario.setDisciplina(null);
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro no método buscarFuncionario");
			e.printStackTrace();
		} finally {
			db.finalizaObjetos(rs, stmt, conn);
		}
		return funcionario;
	}
}
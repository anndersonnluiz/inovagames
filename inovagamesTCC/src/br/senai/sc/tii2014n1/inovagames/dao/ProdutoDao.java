package br.senai.sc.tii2014n1.inovagames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.sc.tii2014n1.inovagames.model.Dominio.Produto;

public class ProdutoDao extends DAO {

	private final String INSERT = "INSERT INTO produto (codigoDeBarras,nome,valor,marca,quantidade,descricao) VALUES (?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE produto SET codigoDeBarras = ?, nome = ?, valor = ?, marca = ?, quantidade = ?,descricao = ?  WHERE idProduto = ?";
	private final String DELETE = "DELETE FROM produto WHERE idProduto = ?";
	private final String SELECT = "SELECT * FROM produto";
	private final String SELECT_ID = "SELECT * FROM produto WHERE idProduto = ?";

	
	public void salvar(Produto produto) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setInt(1, produto.getCodigoDeBarras());
			ps.setString(2, produto.getNome());
			ps.setDouble(3, produto.getValor());
			ps.setString(4, produto.getMarca());
			ps.setInt(5, produto.getQuantidade());
			ps.setString(6, produto.getDescricao());
			//ps.setLong(7, produto.getCategoria().getIdCategoria());Chave estrangeira
			//ps.setLong(8, produto.getPlataforma().getIdPlataforma());Chave estrangeira
			ps.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Erro ao executar o insert: " + ex);
		} finally {
			getConnection();
		}
	}

	public void alterar(Produto produto) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setInt(1, produto.getCodigoDeBarras());
			ps.setString(2, produto.getNome());
			ps.setDouble(3, produto.getValor());
			ps.setString(4, produto.getMarca());
			ps.setInt(5, produto.getQuantidade());
			ps.setString(6, produto.getDescricao());
			//ps.setLong(7, produto.getCategoria().getIdCategoria());Chave estrangeira
			//ps.setLong(8, produto.getPlataforma().getIdPlataforma());Chave estrangeira
			ps.setLong(9, produto.getIdProduto());

			ps.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Erro ao executar o update: " + ex);
		} finally {
			getConnection();
		}

	}

	public void excluir(Integer id) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Erro a executar o delete: " + ex);
		} finally {
			getConnection();
		}
	}

	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = null;
			rs = ps.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setCodigoDeBarras(rs.getInt("codigoDeBarras"));
				produto.setNome(rs.getString("Nome"));
				produto.setValor(rs.getDouble("Valor"));
				produto.setMarca(rs.getString("Marca"));
				produto.setQuantidade(rs.getInt("Quantidade"));
				produto.setDescricao(rs.getString("Descricao"));
				produto.setIdProduto(rs.getLong("idProduto"));

				//CategoriaDao categoriaDao = DAOFactory.getCategoriaDao();
				//produto.setCategoria(categoriaDao.buscarPorId(rs
						//.getLong("idCategoria")));// chave estrangeira

				//PlataformaDao plataformaDao = DAOFactory.getPlataformaDao();
				//produto.setPlataforma(plataformaDao.buscarPorId(rs
						//.getLong("idPlataforma")));// chave estrangeira
				produtos.add(produto);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o select do Produto: " + ex);
		} finally {
			getConnection();
		}
		return produtos;
	}

	

	public Produto buscarPorId(long id) {
		Produto produto = null;
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ResultSet rs = null;
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				produto = new Produto();
				produto.setCodigoDeBarras(rs.getInt("codigoDeBarras"));
				produto.setNome(rs.getString("nome"));
				produto.setValor(rs.getDouble("valor"));
				produto.setMarca(rs.getString("marca"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setIdProduto(rs.getLong("idProduto"));

			}
		} catch (SQLException ex) {
			System.out.println("Erro ao executar o select por ID: " + ex);
		} finally {
			getConnection();
		}
		return produto;
	}

}
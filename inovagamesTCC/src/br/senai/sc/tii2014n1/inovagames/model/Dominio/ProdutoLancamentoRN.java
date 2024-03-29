package br.senai.sc.tii2014n1.inovagames.model.Dominio;

import java.util.List;

import br.senai.sc.tii2014n1.inovagames.dao.ProdutoLancamentoDao;

public class ProdutoLancamentoRN {

	private ProdutoLancamentoDao dao;
	
	public ProdutoLancamentoRN() {
		dao = new ProdutoLancamentoDao();
	}
	
	public void salvar(Produtolancamento produtoLancamento) throws Exception{
		 if (produtoLancamento.getNome().equalsIgnoreCase("")) {
			 throw new Exception("O nome � obrigatorio");
		}
		 
		dao.salvar(produtoLancamento);
		  
	 }
	
	public List<Produtolancamento> listar(String sql){
		return dao.listar(sql);
	}
	
	public void excluir(Integer id) throws Exception{
		dao.excluir(id);
	}
}

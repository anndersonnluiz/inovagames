package br.senai.sc.tii2014n1.inovagames.managebean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.senai.sc.tii2014n1.inovagames.model.Dominio.Produtolancamento;
import br.senai.sc.tii2014n1.inovagames.model.Dominio.ProdutoLancamentoRN;

@ManagedBean
@SessionScoped
public class ProdutoLancamentoMB {
	private Produtolancamento produtoLancamento;
	private List<Produtolancamento> produtoLancamentos;
	private ProdutoLancamentoRN produtoLancamentoRN;
	private Produtolancamento produtoLancamentoSelecionado;
	private String sql;
	
	@PostConstruct
	public void init(){
		produtoLancamentoRN = new ProdutoLancamentoRN();
		if (produtoLancamento == null) {
			produtoLancamento = new Produtolancamento();
		}
			
	}
	
	
	
	public String getSql() {
		return sql;
	}



	public void setSql(String sql) {
		this.sql = sql;
	}



	public Produtolancamento getProdutoLancamentoSelecionado() {
		return produtoLancamentoSelecionado;
	}

	public void setProdutoLancamentoSelecionado(
			Produtolancamento produtoLancamentoSelecionado) {
		this.produtoLancamentoSelecionado = produtoLancamentoSelecionado;
	}

	public List<Produtolancamento> getProdutoLancamentos() {
		if (produtoLancamentos == null) {
			produtoLancamentos = produtoLancamentoRN.listar(sql);
		}
		return produtoLancamentos;
	}
	
	public void setProdutoLancamentos(List<Produtolancamento> produtoLancamentos) {
		this.produtoLancamentos = produtoLancamentos;
	}
	
	

	public Produtolancamento getProdutoLancamento() {
		return produtoLancamento;
	}

	public void setProdutoLancamento(Produtolancamento produtoLancamento) {
		this.produtoLancamento = produtoLancamento;
	}


	
	public String salvar() {
		try {
			produtoLancamentoRN.salvar(produtoLancamento);
			produtoLancamento = new Produtolancamento();
			produtoLancamento = null;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	public String excluir(String idParam){
		int id = Integer.parseInt(idParam);
		try {
			produtoLancamentoRN.excluir(id);
			produtoLancamentos.remove(produtoLancamentoSelecionado);
			produtoLancamentoSelecionado = null;
			FacesContext.getCurrentInstance().addMessage(
					null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Jogo removido com sucesso!", ""));
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
	
	public String voltar() {
		return "administradores";
	}
	
	public String Salvar(){
		try {
			produtoLancamentoRN.salvar(produtoLancamento);
			return "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}

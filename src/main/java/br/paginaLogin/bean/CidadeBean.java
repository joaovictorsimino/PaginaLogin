package br.paginaLogin.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.paginaLogin.dao.CidadeDao;
import br.paginaLogin.dao.EstadoDao;
import br.paginaLogin.domain.Cidade;
import br.paginaLogin.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {
	private List<Cidade> cidades;
	private Cidade cidade;
	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try {
			CidadeDao cidadeDao = new CidadeDao();
			cidades = cidadeDao.listar();

		} catch (Exception e) {
			Messages.addGlobalInfo("Ocorreu um problema ao tentar listar as Cidades!!!");
			e.printStackTrace();
		}
	}

	public void novo() {
		try {
			cidade = new Cidade();
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar("nome");

		} catch (Exception e) {
			Messages.addGlobalInfo("Ocorreu um problema ao gerar uma nova Cidade!!!");
			e.printStackTrace();
		}

	}
	
	public void salvar() {
		try {
			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.merge(cidade);
			cidade =new Cidade();
			
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();
			
			cidades = cidadeDao.listar();
			Messages.addGlobalInfo("Cidade salva com sucesso!!!");

		} catch (Exception e) {
			Messages.addGlobalInfo("Ocorreu um problema ao salvar uma Cidade!!!");
			e.printStackTrace();
		}
		
		
	}
	public void excluir(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			CidadeDao cidadeDao = new CidadeDao();
			cidadeDao.excluir(cidade);
			cidades = cidadeDao.listar();
			Messages.addGlobalInfo("A cidade : " + cidade.getNome() + "  foi excluída com sucesso!!!");
			
		}catch (RuntimeException e) {
			Messages.addGlobalInfo("Aconteceu um erro!!!");
			e.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento) {
		try {
			cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");
			
			EstadoDao estadoDao = new EstadoDao();
			estados = estadoDao.listar();
			
			//Messages.addGlobalInfo("O estado: " + estado.getNome() + "  foi editado com sucesso!!!");
			
		}catch (RuntimeException e) {
			Messages.addGlobalInfo("Aconteceu um erro ao tentar solucionar uma cidade!!!");
			e.printStackTrace();
		}
		
		
		
	}
	
	

}

package br.paginaLogin.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.paginaLogin.dao.EstadoDao;
import br.paginaLogin.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {
	private Estado estado;
	private List<Estado> estados;
	
	public List<Estado> getEstados() {
		return estados;
	}
	
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@PostConstruct
	public void listar() {
		try {
			EstadoDao estadoDao = new EstadoDao();
			estados= estadoDao.listar();
			
		}catch (Exception e) {
			Messages.addGlobalInfo("Ocorreu um problema ao tentar listar os Estados!!!");
			e.printStackTrace();
		}
	}

	public void novo() {
		estado = new Estado();
	}

	public void salvar() {
		try {
			EstadoDao estadoDao = new EstadoDao();
			estadoDao.merge(estado);
			
			novo();
			estados = estadoDao.listar();
			
			Messages.addGlobalInfo("Estado salvo com sucesso!!");
			
			 
		}catch (RuntimeException e) {
			Messages.addGlobalInfo("Ocorreu um problema ao tentar salvar!!!");
			e.printStackTrace();
		}
		
		
	}
	
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			EstadoDao estadoDao = new EstadoDao();
			estadoDao.excluir(estado);
			estados = estadoDao.listar();
			Messages.addGlobalInfo("O estado: " + estado.getNome() + "  foi excluído com sucesso!!!");
			
		}catch (RuntimeException e) {
			Messages.addGlobalInfo("Aconteceu um erro!!!");
			e.printStackTrace();
		}
		
		
		
	}
	
	public void editar(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			
			//Messages.addGlobalInfo("O estado: " + estado.getNome() + "  foi editado com sucesso!!!");
			
		}catch (RuntimeException e) {
			Messages.addGlobalInfo("Aconteceu um erro!!!");
			e.printStackTrace();
		}
		
		
		
	}
	
	
}

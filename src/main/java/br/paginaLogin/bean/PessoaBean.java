package br.paginaLogin.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.paginaLogin.dao.CidadeDao;
import br.paginaLogin.dao.EstadoDao;
import br.paginaLogin.dao.PessoaDao;
import br.paginaLogin.domain.Cidade;
import br.paginaLogin.domain.Estado;
import br.paginaLogin.domain.Pessoa;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PessoaBean implements Serializable {
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	private Estado estado;
	private List<Estado> estados;
	private List<Cidade> cidades;
	
	
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	@PostConstruct
	public void listar() {
		try{
			PessoaDao pessoaDAO = new PessoaDao();
			pessoas = pessoaDAO.listar();
		}catch(RuntimeException erro){
			Messages.addGlobalError("Ocorreu um erro ao tentar listar as pessoas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			pessoa = new Pessoa();

			EstadoDao estadoDAO = new EstadoDao();
			estados = estadoDAO.listar();

			cidades = new ArrayList<Cidade>();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova pessoa");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		pessoa = (Pessoa) evento.getComponent().getAttributes().get("pessoaSelecionada");
		estado = pessoa.getCidade().getEstado();
		
		EstadoDao estadoDao = new EstadoDao();
		estados = estadoDao.listar("nome");
		
	
	}

	public void salvar() {
		try {
			PessoaDao pessoaDao = new PessoaDao();
			pessoaDao.merge(pessoa);
			
			pessoas = pessoaDao.listar("nome");
			
			CidadeDao cidadeDao = new CidadeDao();
			cidades = cidadeDao.buscarPorEstado(estado.getCodigo());

			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar a pessoa");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
				
	}
	
	public void popular() {
		if(estado!= null) {
			try{
				CidadeDao cidadeDao = new CidadeDao();
				cidades = cidadeDao.buscarPorEstado(estado.getCodigo());
				//novo();
				
			}catch(RuntimeException erro){
				Messages.addGlobalError("Ocorreu um erro ao tentar listar as cidades");
				erro.printStackTrace();
			}
		}else {
			cidades = new ArrayList<>();
		}
		
	}
	
}

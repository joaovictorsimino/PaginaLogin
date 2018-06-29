package br.paginaLogin.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.paginaLogin.dao.ClienteDao;
import br.paginaLogin.dao.PessoaDao;
import br.paginaLogin.domain.Cliente;
import br.paginaLogin.domain.Pessoa;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {
	private Cliente cliente;

	private List<Cliente> clientes;
	private List<Pessoa> pessoas;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	@PostConstruct
	public void listar() {
		try {
			ClienteDao clienteDAO = new ClienteDao();
			clientes = clienteDAO.listar("dataCadastro");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os clientes");
			erro.printStackTrace();
			
		}
	}

	public void novo() {
		try {
			cliente = new Cliente();

			PessoaDao pessoaDAO = new PessoaDao();
			pessoas = pessoaDAO.listar("nome");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo cliente");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			ClienteDao clienteDAO = new ClienteDao();
			clienteDAO.merge(cliente);

			cliente = new Cliente();
			
			clientes = clienteDAO.listar("dataCadastro");

			PessoaDao pessoaDAO = new PessoaDao();
			pessoas = pessoaDAO.listar("nome");
			
			Messages.addGlobalInfo("Cliente salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
			erro.printStackTrace();
		}
	}
}
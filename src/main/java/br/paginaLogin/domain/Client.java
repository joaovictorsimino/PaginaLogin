package br.paginaLogin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Client extends GenericDomain {
	//@SuppressWarnings("unused")
	@Column(length = 50, nullable = false)
	private String nome;
	@OneToMany
	@JoinColumn(nullable = false)
	private Product produto;
	@Column(length = 20, nullable = false)
	private String ip;
	@Column(length = 50, nullable = false)
	private String password;
	@Column(length = 2, nullable = false)
	private Integer porta;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Product getProduto() {
		return produto;
	}
	public void setProduto(Product produto) {
		this.produto = produto;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	
}

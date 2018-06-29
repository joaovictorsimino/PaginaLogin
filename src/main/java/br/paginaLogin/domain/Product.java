package br.paginaLogin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;


@SuppressWarnings("serial")
@Entity
public class Product extends GenericDomain {
	//@SuppressWarnings("unused")
	@Column(length = 50, nullable = false)
	private String nome;
		public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Column(length = 2, nullable = true)
	private Integer Complemento;
	public Integer getComplemento() {
		return Complemento;
	}
	public void setComplemento(Integer complemento) {
		Complemento = complemento;
	}
	

	
}

package br.paginaLogin.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericDomain {
	@Column(length = 50, nullable = false)
	private String trabalho;
	@Column(nullable =false)
	@Temporal(TemporalType.DATE)
	private Date dataAdmissao;
	@OneToOne
	@JoinColumn(nullable=false)
	private Pessoa pessoa;
	public String getTrabalho() {
		return trabalho;
	}
	public void setTrabalho(String trabalho) {
		this.trabalho = trabalho;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	

}

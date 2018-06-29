package br.paginaLogin.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.paginaLogin.dao.CidadeDao;
import br.paginaLogin.dao.EstadoDao;
import br.paginaLogin.domain.Cidade;
import br.paginaLogin.domain.Estado;

public class CidadeDAOTest {
	@Test
	@Ignore
	public void salvar() {
		EstadoDao estadoDAO = new EstadoDao();
		Estado estado = estadoDAO.buscar(2L);
		
		Cidade cidade = new Cidade();
		cidade.setNome("Rio de Janeiro");
		cidade.setEstado(estado);
		
		CidadeDao cidadeDao = new CidadeDao();
		cidadeDao.salvar(cidade);
		System.out.println(cidade);
		
	}
}

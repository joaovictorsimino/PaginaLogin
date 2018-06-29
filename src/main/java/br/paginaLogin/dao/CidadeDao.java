package br.paginaLogin.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.paginaLogin.domain.Cidade;
import br.paginaLogin.util.HibernateUtil;

public class CidadeDao extends GenericDao<Cidade> {
	@SuppressWarnings("unchecked")
	public List<Cidade> buscarPorEstado(Long estadoCodigo){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Cidade.class);
			consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
			consulta.addOrder(Order.asc("nome"));
			List<Cidade> resultado = consulta.list();
			return resultado;
		}catch (RuntimeException e) {
			throw e;
		}finally {
			sessao.close();
		}
	}
}

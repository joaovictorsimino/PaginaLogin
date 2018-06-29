package br.paginaLogin.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.google.gson.Gson;

import br.paginaLogin.dao.FabricanteDao;
import br.paginaLogin.domain.Fabricante;

@Path("fabricante")
public class FabricanteService {
		@GET
		public String listar() {
			FabricanteDao fabricanteDao = new FabricanteDao();
			List<Fabricante> fabricantes = fabricanteDao.listar("descricao");
			
			Gson gson =new Gson();
			String json = gson.toJson(fabricantes);
			return json;
		}
		@GET
		@Path("{codigo}")
		public String buscar(@PathParam("codigo") Long codigo) {
			FabricanteDao fabricanteDao = new FabricanteDao();
			Fabricante fabricante = fabricanteDao.buscar(codigo);
			Gson gson =new Gson();
			String json = gson.toJson(fabricante);
			return json;
		}
		
		@POST
		public String salvar(String json) {
			Gson gson =new Gson();
			Fabricante fabricante =  gson.fromJson(json, Fabricante.class);
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricanteDao.merge(fabricante);
			
			String JsonSaida = gson.toJson(fabricante);
			return JsonSaida;
		
		}
		@DELETE
		public String excluir(String json) {
			Gson gson =new Gson();
			Fabricante fabricante =  gson.fromJson(json, Fabricante.class);
			FabricanteDao fabricanteDao = new FabricanteDao();
			fabricante = fabricanteDao.buscar(fabricante.getCodigo());
			
			fabricanteDao.excluir(fabricante);
			
			String saida = gson.toJson(fabricante);
			return saida;
			
			
		}
		
		
		
		
}

package br.paginaLogin.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("drogaria")
public class DrogariaService {
	@GET
	public String exibir() {
		return "Curso de Java";
	}
	
}

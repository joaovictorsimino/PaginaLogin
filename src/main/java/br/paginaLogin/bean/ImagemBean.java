package br.paginaLogin.bean;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class ImagemBean {
	@ManagedProperty("#{param.caminho}")
	private String caminho;
	private StreamedContent foto;
	public String getCamihho() {
		return caminho;
	}
	public void setCamihho(String camihho) {
		this.caminho = camihho;
	}
	public StreamedContent getFoto() throws IOException {
		if(caminho == null || caminho.isEmpty() ) {
			Path path = Paths.get("/home/joaovictor/eclipse-workspace/uploads/branco.png");
			InputStream stream = Files.newInputStream(path);
			foto = new DefaultStreamedContent(stream);
		}else {
			Path path = Paths.get(caminho);
			InputStream fluxo = Files.newInputStream(path);
			foto = new DefaultStreamedContent(fluxo);
		}
		
		return foto;
	}
	
	public void setFoto(StreamedContent foto) {
		this.foto = foto;
	}
	
	

}

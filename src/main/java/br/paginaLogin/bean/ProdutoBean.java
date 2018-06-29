package br.paginaLogin.bean;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.paginaLogin.dao.FabricanteDao;
import br.paginaLogin.dao.ProdutoDao;
import br.paginaLogin.domain.Fabricante;
import br.paginaLogin.domain.Produto;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto;
	private List<Produto> produtos;
	private List<Fabricante> fabricantes;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {
			ProdutoDao produtoDAO = new ProdutoDao();
			produtos = produtoDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os produtos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			produto = new Produto();

			FabricanteDao fabricanteDAO = new FabricanteDao();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar um novo produto");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			FabricanteDao fabricanteDAO = new FabricanteDao();
			fabricantes = fabricanteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um produto");
			erro.printStackTrace();
		}
	}

	public void salvar() throws IOException {
		try {
			ProdutoDao produtoDAO = new ProdutoDao();
			Produto produtoRetorno = produtoDAO.merge(produto);
			
			Path origem = Paths.get(produto.getCaminho());
			Path destino = Paths.get("/home/joaovictor/eclipse-workspace/uploads/" + produtoRetorno.getCodigo()+".png");
			
			Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
			
			produto = new Produto();

			FabricanteDao fabricanteDAO = new FabricanteDao();
			fabricantes = fabricanteDAO.listar();
			
			
			produtos = produtoDAO.listar();
			
			

			Messages.addGlobalInfo("Produto salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar o produto");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			produto = (Produto) evento.getComponent().getAttributes().get("produtoSelecionado");

			ProdutoDao produtoDAO = new ProdutoDao();
			produtoDAO.excluir(produto);

			produtos = produtoDAO.listar();
			
			Path arquivo = Paths.get("/home/joaovictor/eclipse-workspace/uploads/" + produto.getCodigo()+".png");
			try {
				Files.deleteIfExists(arquivo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Messages.addGlobalInfo("Produto removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o produto");
			erro.printStackTrace();
		}
	}

	public void upload(FileUploadEvent evento) {
		UploadedFile file = evento.getFile();
		try {
			Path arquivoTemp = Files.createTempFile(null, null);
			Files.copy(file.getInputstream(), arquivoTemp,StandardCopyOption.REPLACE_EXISTING);
			produto.setCaminho(arquivoTemp.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Messages.addGlobalInfo("Ocorreu um erro durante o upload do arquivo");
		}
		
		// application code
	}

}
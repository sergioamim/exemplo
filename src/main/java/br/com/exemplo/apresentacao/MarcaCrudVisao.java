package br.com.exemplo.apresentacao;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;

import br.com.exemplo.dominio.entidade.Marca;
import br.com.exemplo.dominio.exception.EntidadeJaCadastradaException;
import br.com.exemplo.dominio.repositorio.MarcaRepositorio;

@Name("marcaCrudVisao")
@Scope(ScopeType.PAGE)
public class MarcaCrudVisao {
	
	@In(create=true)
	private MarcaRepositorio marcaRepositorio;
	
	@In(create=true)
	@Out(value="marca",required=false)
	@DataModelSelection
	private Marca marca;
	
	private String pesquisaNome;
	
	@DataModel
	private List<Marca> marcas = new ArrayList<Marca>();
	
	@In
	private FacesMessages facesMessages;
	
	private EstadoPaginaEnum estadoAtualPagina = EstadoPaginaEnum.PESQUISA;
	
	public void novo(){
		marca = new Marca();
		estadoAtualPagina = EstadoPaginaEnum.INCLUSAO;
	}
	
	public void salvar(){
		try{
		definirInclusaoOuEdicao();
		pesquisar();
		}
		catch (EntidadeJaCadastradaException e) {
			facesMessages.addToControl("txtNome",e.getMessage());
		}
	}
	
	public void excluir(){
		marcaRepositorio.remover(marca);
		facesMessages.add("Marca " + marca.getNome() + " excluída com sucesso!");
		pesquisar();
	}
	
	public void  editar(){
		estadoAtualPagina = EstadoPaginaEnum.EDICAO;
	}
	
	public void pesquisar(){
		estadoAtualPagina = EstadoPaginaEnum.PESQUISA;
		marcas.clear();
		marcas.addAll(marcaRepositorio.consultarPorNome(pesquisaNome));
		pesquisaNome = "";
	}
	
	
	private void definirInclusaoOuEdicao() throws EntidadeJaCadastradaException{
		if(marca.getId() == null){
			marcaRepositorio.adicionar(marca);
			facesMessages.add("Marca #{marca.nome} incluída com sucesso!");
		}
		else{
			marcaRepositorio.atualizar(marca);
			facesMessages.add("Marca #{marca.nome} autualizada com sucesso!");
		}
	
	}
	
	
	public void carregarMarcas(){
		if(marcas.size()==0){
			marcas.clear();
			marcas.addAll(marcaRepositorio.listar());
		}
	}
	
	
	public boolean isEstadoDePesquisa() {
		return (estadoAtualPagina == null || estadoAtualPagina.equals(EstadoPaginaEnum.PESQUISA));
	}
	public boolean isEstadoDeInclusao() {
		return estadoAtualPagina.equals(EstadoPaginaEnum.INCLUSAO);
	}
	public boolean isEstadoDeEdicao() {
		return estadoAtualPagina.equals(EstadoPaginaEnum.EDICAO);
	}

	public String getPesquisaNome() {
		return pesquisaNome;
	}

	public void setPesquisaNome(String pesquisaNome) {
		this.pesquisaNome = pesquisaNome;
	}
	

}

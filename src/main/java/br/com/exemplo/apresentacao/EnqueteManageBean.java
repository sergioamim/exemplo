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

import br.com.exemplo.dominio.entidade.Enquete;
import br.com.exemplo.dominio.entidade.Opcao;
import br.com.exemplo.dominio.exception.EntidadeJaCadastradaException;
import br.com.exemplo.dominio.repositorio.EnqueteRepositorio;

@Name("enqueteManageBean")
@Scope(ScopeType.SESSION)
public class EnqueteManageBean {
	
	@In(create=true)
	private EnqueteRepositorio enqueteRepositorio;
	
	@In(create=true)
	@Out(value="enquete",required=false)
	@DataModelSelection
	private Enquete enquete;
	
	public Opcao getOpcaoSelecionada() {
		return opcaoSelecionada;
	}

	public void setOpcaoSelecionada(Opcao opcaoSelecionada) {
		this.opcaoSelecionada = opcaoSelecionada;
	}

	/*@In(create=true)
	 */
	@In(create=true)
	@Out(value="opcao",required=false)
	private Opcao opcao;
	
	private Opcao opcaoSelecionada;
	
	private String pesquisaNome;
	
	@DataModel
	private List<Enquete> enquetes;
	
	@In
	private FacesMessages facesMessages;
	
	private EstadoPaginaEnum estadoAtualPagina = EstadoPaginaEnum.PESQUISA;
	
	public void novo(){
		enquete = new Enquete();
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
		enqueteRepositorio.remover(enquete);
		facesMessages.add("Enquete " + enquete.getPergunta() + " excluída com sucesso!");
		pesquisar();
	}
	
	public void  editar(){
		estadoAtualPagina = EstadoPaginaEnum.EDICAO;
	}
	
	public void pesquisar(){
		estadoAtualPagina = EstadoPaginaEnum.PESQUISA;
		enquetes.clear();
		enquetes.addAll(enqueteRepositorio.consultarPorPergunta(pesquisaNome));
		pesquisaNome = "";
	}
	
	
	private void definirInclusaoOuEdicao() throws EntidadeJaCadastradaException{
		if(enquete.getId() == null){
			enqueteRepositorio.adicionar(enquete);
			facesMessages.add("Marca #{enquete.pergunta} incluída com sucesso!");
		}
		else{
			enqueteRepositorio.atualizar(enquete);
			facesMessages.add("Enquete #{enquete.pergunta} autualizada com sucesso!");
		}
	
	}
	
	public void adcionarOpcao() {
		enquete.getOpcoes().add(opcao);
		opcao = new Opcao();
	}
	
	public void removerOpcao() {
		enquete.getOpcoes().remove(opcaoSelecionada);
	}
	
	public void carregarEnquetes(){
		
		if(enquetes == null){
			enquetes = enqueteRepositorio.listar();
			//enquetes = new ArrayList<Enquete>();
			//enquetes.clear();
			//enquetes.addAll();
		}
	}
	
	public Enquete getEnqueteAtual() {
		return enqueteRepositorio.recuperaAtual();
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

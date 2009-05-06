package br.com.exemplo.dominio.repositorio;

import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import br.com.exemplo.dominio.entidade.Enquete;
import br.com.exemplo.dominio.entidade.Marca;
import br.com.exemplo.dominio.exception.EntidadeJaCadastradaException;

@Name("enqueteRepositorio")
public class EnqueteRepositorio {

	@In(create = true)
	private DataMapper<Enquete> dataMapper;
	
	@Transactional
	public void adicionar(Enquete enquete) throws EntidadeJaCadastradaException{
		if(dataMapper.consultarPorCampo(Enquete.class, "pergunta", enquete.getPergunta()).size() > 0){
			throw new EntidadeJaCadastradaException(enquete.getPergunta() + " ja foi cadastrada!");
		}
		enquete.setDataInclusao(new Date());
		dataMapper.salvar(enquete);
	}
	
	@Transactional
	public Enquete recuperaAtual(){
		List<Enquete> enquetes = dataMapper.getEntityManager().createQuery("select e from Enquete e order by e.dataInclusao asc").getResultList();
		if(enquetes.size() > 0) {
			return (Enquete) enquetes.get(0);
		} else 
			return null;
	}
	
	@Transactional
	public void atualizar(Enquete obj){
		dataMapper.atualizar(obj);
	}
	
	@Transactional
	public void remover(Enquete obj){
		dataMapper.excluir(obj);
	}
	
	@Transactional
	public List<Enquete> consultarPorPergunta(String pergunta){
		return dataMapper.consultarPorCampo(Enquete.class, "pergunta", pergunta);
	}
	
	@Transactional
	public List<Enquete> listar(){
		return dataMapper.listar(Enquete.class);
	}
}

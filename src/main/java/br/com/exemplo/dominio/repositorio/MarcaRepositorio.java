package br.com.exemplo.dominio.repositorio;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

import br.com.exemplo.dominio.entidade.Marca;
import br.com.exemplo.dominio.exception.EntidadeJaCadastradaException;

@Name("marcaRepositorio")
public class MarcaRepositorio {
	
	@In(create = true)
	private DataMapper<Marca> dataMapper;
	
	@Transactional
	public void adicionar(Marca marca) throws EntidadeJaCadastradaException{
		if(consultarPorNome(marca.getNome()).size() > 0){
			throw new EntidadeJaCadastradaException(marca.getNome() + " ja foi cadastrada!");
		}
		dataMapper.salvar(marca);
	}
	
	@Transactional
	public void atualizar(Marca marca){
		dataMapper.atualizar(marca);
	}
	
	@Transactional
	public void remover(Marca marca){
		dataMapper.excluir(marca);
	}
	
	@Transactional
	public Marca recuperar(Long id){
		return dataMapper.recuperar(Marca.class, id);
	}
	
	@Transactional
	public List<Marca> consultarPorNome(String nome){
		return dataMapper.consultarPorCampo(Marca.class, "nome", nome);
	}
	
	@Transactional
	public List<Marca> listar(){
		return dataMapper.listar(Marca.class);
	}

}

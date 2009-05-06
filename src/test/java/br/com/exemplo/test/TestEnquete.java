package br.com.exemplo.test;

import org.jboss.seam.annotations.In;

import br.com.exemplo.dominio.entidade.Enquete;
import br.com.exemplo.dominio.exception.EntidadeJaCadastradaException;
import br.com.exemplo.dominio.repositorio.EnqueteRepositorio;

public class TestEnquete {

	@In(create = true)
	private EnqueteRepositorio repo;
	
	//@Test
	public void testNovaEnquete() {
		
		Enquete enquete = new Enquete();
		enquete.setPergunta("Ser�?");
		enquete.setDataInclusao(new java.util.Date());
		
		try {
			repo.adicionar(enquete);
		} catch (EntidadeJaCadastradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

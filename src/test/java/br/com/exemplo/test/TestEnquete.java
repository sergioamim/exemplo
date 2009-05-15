package br.com.exemplo.test;

import org.jboss.seam.Component;
import org.junit.Test;

import br.com.exemplo.dominio.entidade.Enquete;
import br.com.exemplo.dominio.exception.EntidadeJaCadastradaException;
import br.com.exemplo.dominio.repositorio.EnqueteRepositorio;


public class TestEnquete {

//	@Test
	public void testNovaEnquete() {
		
		Enquete enquete = new Enquete();
		enquete.setPergunta("Seraaaa?");
		enquete.setDataInclusao(new java.util.Date());
		EnqueteRepositorio repo = (EnqueteRepositorio) Component.getInstance(EnqueteRepositorio.class);
		try {
			repo.adicionar(enquete);
		} catch (EntidadeJaCadastradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

package br.com.exemplo.dominio.exception;

public class EntidadeNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String msg){
		super(msg);
	}

}

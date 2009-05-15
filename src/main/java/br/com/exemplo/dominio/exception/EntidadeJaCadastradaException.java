package br.com.exemplo.dominio.exception;

public class EntidadeJaCadastradaException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EntidadeJaCadastradaException(String msg){
		super(msg);
	}

}

package br.com.exemplo.dominio.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.NotNull;

@Entity
public class Opcao {
	
	@Id @GeneratedValue
	private Long id;

	@NotNull
	private String opcao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
}

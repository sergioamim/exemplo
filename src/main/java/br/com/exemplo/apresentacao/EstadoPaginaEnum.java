package br.com.exemplo.apresentacao;

public enum EstadoPaginaEnum {
	
    INCLUSAO("Inclusao"), EDICAO("Edicao"), PESQUISA("Pesquisa");

    private final String descricao;
    
    private EstadoPaginaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String toString() {
        return descricao;
    }

}

package com.zupacademy.proposta.novaproposta;

public class DetalhePropostaResponse {

	private String nome;
	private String email;
	

	public DetalhePropostaResponse(NovaProposta proposta) {
		super();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
}
	
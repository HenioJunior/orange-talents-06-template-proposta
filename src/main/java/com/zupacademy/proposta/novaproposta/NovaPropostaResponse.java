package com.zupacademy.proposta.novaproposta;

public class NovaPropostaResponse {
	
	private long id;
	private String nome;
	private String email;
	
	public NovaPropostaResponse(NovaProposta proposta) {
		this.id = proposta.getId();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
	}

	public long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "NovaPropostaResponse [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
}

package com.zupacademy.proposta.novaproposta;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.zupacademy.proposta.novatransacao.NovaTransacaoResponse;

public class DetalhePropostaResponse {

	private String nome;
	private String email;
	private Set<NovaTransacaoResponse> transacao = new HashSet<>();

	public DetalhePropostaResponse(NovaProposta proposta) {
		super();
		this.nome = proposta.getNome();
		this.email = proposta.getEmail();
		this.transacao = proposta.getTransacoes().stream().map(transacao -> new NovaTransacaoResponse(transacao))
				.collect(Collectors.toSet());
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	
	public Set<NovaTransacaoResponse> getTransacao() {
		return transacao;
	}
}

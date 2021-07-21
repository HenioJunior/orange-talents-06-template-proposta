package com.zupacademy.proposta.solicitacaoanalise;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.novaproposta.NovaProposta;

public class SolicitacaoAnaliseRequest {
		
	@NotBlank
	private String nome;
	
	@NotBlank
	private String documento;
	@NotNull
	private long idProposta;
				
	public SolicitacaoAnaliseRequest() {
		super();
	}

	public SolicitacaoAnaliseRequest(@NotBlank String nome, @NotBlank String documento, @NotNull long idProposta) {
		this.nome = nome;
		this.documento = documento;
		this.idProposta = idProposta;
	}

	public SolicitacaoAnaliseRequest(NovaProposta novaProposta) {
		this.nome = novaProposta.getNome();
		this.documento = novaProposta.getDocumento();
		this.idProposta = novaProposta.getId();
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

	public long getIdProposta() {
		return idProposta;
	}
			
}

package com.zupacademy.proposta.fluxotransacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.novaproposta.NovaProposta;
import com.zupacademy.proposta.validacao.CPFOrCNPJ;

public class SolicitaAnaliseRequest {
		
	@NotBlank
	private String nome;
	@CPFOrCNPJ
	@NotBlank
	private String documento;
	@NotNull
	private long idProposta;
				
	public SolicitaAnaliseRequest() {
		super();
	}
	
	public SolicitaAnaliseRequest(@NotBlank String nome, @NotBlank String documento, @NotNull long idProposta) {
		super();
		this.nome = nome;
		this.documento = documento;
		this.idProposta = idProposta;
	}

	public SolicitaAnaliseRequest(NovaProposta novaProposta) {
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
	
	
	public NovaTransacao toTransacao(NovaProposta proposta, boolean statusProposta) {
		
		StatusTransacao status = statusProposta == true? StatusTransacao.ELEGIVEL : StatusTransacao.NAO_ELEGIVEL;
				
		return new NovaTransacao(nome, documento, idProposta, status, proposta);
	}
				
}

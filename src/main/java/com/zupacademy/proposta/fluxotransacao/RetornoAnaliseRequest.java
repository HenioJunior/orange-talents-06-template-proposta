package com.zupacademy.proposta.fluxotransacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.validacao.CPFOrCNPJ;

public class RetornoAnaliseRequest {

	@NotBlank
	private String nome;
	@CPFOrCNPJ
	@NotBlank
	private String documento;
	@NotNull
	private long idProposta;

	@NotBlank
	private String resultadoSolicitacao;

	public RetornoAnaliseRequest() {
		super();
	}

	public RetornoAnaliseRequest(@NotBlank String nome, @NotBlank String documento, @NotNull long idProposta,
			@NotBlank String resultadoSolicitacao) {
		this.nome = nome;
		this.documento = documento;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
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

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	@Override
	public String toString() {
		return "Retorno da Análise: [nome=" + nome + ", documento=" + documento + ", idProposta=" + idProposta
				+ ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}
}

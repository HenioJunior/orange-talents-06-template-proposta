package com.zupacademy.proposta.solicitacaoanalise;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.novaproposta.NovaProposta;
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
		
	public RetornoAnaliseRequest(@NotBlank String nome, @NotBlank String documento, @NotNull long idProposta,
			@NotBlank String resultadoSolicitacao) {
		this.nome = nome;
		this.documento = documento;
		this.idProposta = idProposta;
		this.resultadoSolicitacao = resultadoSolicitacao;
	}
	
	public RetornoAnaliseRequest(NovaProposta proposta) {
		this.nome = proposta.getNome();
		this.documento = proposta.getDocumento();
		this.idProposta = proposta.getId();
		this.resultadoSolicitacao = proposta.getStatus().toString();
	}

	public long getIdProposta() {
		return idProposta;
	}
	
	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento, idProposta, resultadoSolicitacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RetornoAnaliseRequest other = (RetornoAnaliseRequest) obj;
		return Objects.equals(documento, other.documento) && idProposta == other.idProposta
				&& resultadoSolicitacao == other.resultadoSolicitacao;
	}

	@Override
	public String toString() {
		return "Retorno da Análise: [nome=" + nome + ", documento=" + documento + ", idProposta=" + idProposta
				+ ", resultadoSolicitacao=" + resultadoSolicitacao + "]";
	}
}

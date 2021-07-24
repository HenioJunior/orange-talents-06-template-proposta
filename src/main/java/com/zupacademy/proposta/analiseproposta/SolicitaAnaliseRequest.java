package com.zupacademy.proposta.analiseproposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zupacademy.proposta.novaproposta.NovaProposta;
import com.zupacademy.proposta.novatransacao.NovaTransacao;
import com.zupacademy.proposta.novatransacao.NovaTransacaoStatus;
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

	public NovaTransacao toTransacao(NovaProposta proposta, boolean statusAnalise) {
		NovaTransacaoStatus status = statusAnalise == true ? NovaTransacaoStatus.ELEGIVEL : NovaTransacaoStatus.NAO_ELEGIVEL;
		return new NovaTransacao(nome, documento, idProposta, status, proposta);
	}

}

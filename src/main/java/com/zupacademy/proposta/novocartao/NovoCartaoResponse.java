package com.zupacademy.proposta.novocartao;

import com.zupacademy.proposta.novaproposta.NovaProposta;

public class NovoCartaoResponse {
	
	private String id;
	private String emitidoEm;
	private String titular;
	private int limite;
	private long idProposta;

	public NovoCartaoResponse(String id, String emitidoEm, String titular, int limite, long idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}

	public NovoCartaoResponse() {
		
	}

	public String getId() {
		return id;
	}

	public String getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public int getLimite() {
		return limite;
	}

	public long getIdProposta() {
		return idProposta;
	}

	@Override
	public String toString() {
		return "Retorno Cartao [id=" + id + ", emitidoEm=" + emitidoEm + ", titular=" + titular + ", limite=" + limite
				+ ", idProposta=" + idProposta + "]";
	}

	public NovoCartao toModel(NovaProposta proposta) {
				
		return new NovoCartao(id, emitidoEm , limite, proposta);
	}
}

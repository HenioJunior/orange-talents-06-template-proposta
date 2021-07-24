package com.zupacademy.proposta.solicitacartao;

public class SolicitaCartaoResponse {
	
	private String id;
	private String emitidoEm;
	private String titular;
	private int limite;
	private long idProposta;

	public SolicitaCartaoResponse(String id, String emitidoEm, String titular, int limite, long idProposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.idProposta = idProposta;
	}

	public SolicitaCartaoResponse() {
		
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
}

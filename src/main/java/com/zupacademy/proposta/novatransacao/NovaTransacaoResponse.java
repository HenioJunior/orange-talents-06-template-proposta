package com.zupacademy.proposta.novatransacao;

import javax.persistence.Enumerated;

public class NovaTransacaoResponse {
	
	private long idProposta;
	@Enumerated
	private NovaTransacaoStatus status;
	
		
	public NovaTransacaoResponse(NovaTransacao transacao) {
		this.idProposta = transacao.getIdProposta();
		this.status = transacao.getStatus();
	}
	public long getIdProposta() {
		return idProposta;
	}
	public NovaTransacaoStatus getStatus() {
		return status;
	}
}

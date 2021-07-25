package com.zupacademy.proposta.novatransacao;

import javax.persistence.Enumerated;

public class NovaTransacaoResponse {
	
	@Enumerated
	private NovaTransacaoStatus status;
	
		
	public NovaTransacaoResponse(NovaTransacao transacao) {
		this.status = transacao.getStatus();
	}
	
	public NovaTransacaoStatus getStatus() {
		return status;
	}
}

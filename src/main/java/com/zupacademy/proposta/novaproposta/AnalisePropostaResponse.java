package com.zupacademy.proposta.novaproposta;

import javax.persistence.Enumerated;

public class AnalisePropostaResponse {
	
	@Enumerated
	private NovaPropostaStatus status;
	
		
	public AnalisePropostaResponse(NovaProposta proposta) {
		this.status = proposta.getStatus();
	}
	
	public NovaPropostaStatus getStatus() {
		return status;
	}
}

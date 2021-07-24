package com.zupacademy.proposta.solicitacartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zupacademy.proposta.fluxotransacao.SolicitaAnaliseRequest;

@Component
@EnableScheduling
public class VerificadorDeCartao {

	@Autowired
	SolicitaCartaoService cartaoService;

	private SolicitaAnaliseRequest request;

	private final long SEGUNDO = 5000;
//	    private final long MINUTO = SEGUNDO * 60; 
//	    private final long HORA = MINUTO * 60;

	public VerificadorDeCartao() {
	}

	@Scheduled(fixedDelay = SEGUNDO)
	public void verificaPorSegundo() {
		if (request != null) {
			cartaoService.cartao(request);
		}
	}

	public void setRequest(SolicitaAnaliseRequest request) {
		this.request = request;
	}

}

package com.zupacademy.proposta.novocartao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;

@Component
@EnableScheduling
public class NovoCartaoSchedule {

	@Autowired
	NovoCartaoService cartaoService;
	@Autowired
	NovoCartaoRepository repository;

	private SolicitaAnaliseRequest request;

	private final long SEGUNDO = 5000;
//	    private final long MINUTO = SEGUNDO * 60; 
//	    private final long HORA = MINUTO * 60;

	public NovoCartaoSchedule() {
	}

	@Scheduled(fixedDelay = SEGUNDO)
	public void verificaPorSegundo() {
		if (request != null) {
			while (naoExisteCartao(request)) {
				cartaoService.emitirCartao(request);
			}
		}
	}

	public void setRequest(SolicitaAnaliseRequest request) {
		this.request = request;
	}

	public boolean naoExisteCartao(SolicitaAnaliseRequest request) {
		Optional<NovoCartao> obj = repository.findById(request.getIdProposta());
		if (!obj.isPresent()) {
			return true;
		}
		return false;
	}

}

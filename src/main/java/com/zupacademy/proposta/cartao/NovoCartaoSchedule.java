package com.zupacademy.proposta.cartao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;
import com.zupacademy.proposta.exceptions.MethodNotValidException;

@Component
@EnableScheduling
public class NovoCartaoSchedule {

	@Autowired
	NovoCartaoService cartaoService;
	@Autowired
	CartaoRepository repository;

	private SolicitaAnaliseRequest request;

	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	private final long HORA = MINUTO * 60;

	public NovoCartaoSchedule() {
		try {
			if (request != null) {
				while (naoExisteCartao(request)) {
					cartaoService.emitirCartao(request);
				}
			}
		} catch(MethodNotValidException e) {
			throw new MethodNotValidException("Erro na requisição.");
		}
	}

	@Scheduled(fixedDelay = MINUTO)
	public void solicitarCartao() {
		
	}

	public void setRequest(SolicitaAnaliseRequest request) {
		this.request = request;
	}

	public boolean naoExisteCartao(SolicitaAnaliseRequest request) {
		Optional<Cartao> obj = repository.findById(request.getIdProposta());
		if (!obj.isPresent()) {
			return true;
		}
		return false;
	}

}

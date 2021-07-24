package com.zupacademy.proposta.fluxotransacao;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zupacademy.proposta.exceptions.DatabaseException;
import com.zupacademy.proposta.novaproposta.NovaProposta;
import com.zupacademy.proposta.novaproposta.NovaPropostaRepository;
import com.zupacademy.proposta.solicitacartao.VerificadorDeCartao;

import feign.FeignException;

@RestController
@RequestMapping(value = "/transacoes/")
public class FluxoTransacaoController {

	@Autowired
	private NovaPropostaRepository repository;

	@Autowired
	private SolicitaAnaliseFeign solicitacaoAnaliseFeign;
	
	private Set<EventoNovaPropostaSucesso> eventosNovaPropostaSucesso;
	
	@Autowired
	private VerificadorDeCartao verificadorCartao;

	@Autowired
	public FluxoTransacaoController(Set<EventoNovaPropostaSucesso> eventosNovaPropostaSucesso) {
		super();
		this.eventosNovaPropostaSucesso = eventosNovaPropostaSucesso;
	}
	
	@Transactional
	@PostMapping(value = "/analise")
	public String enviarPropostaParaAnalise(@RequestBody @Valid SolicitaAnaliseRequest request) {
		boolean status = verificarStatus(request);

		return processa(request, status);
	}

	public boolean verificarStatus(@RequestBody @Valid SolicitaAnaliseRequest request) {
		NovaProposta proposta = new NovaProposta();
		try {
			proposta = buscarProposta(request.getIdProposta());
			RetornoAnaliseRequest analise = solicitacaoAnaliseFeign
					.enviarParaAnalise(new SolicitaAnaliseRequest(proposta));
			if (analise.getResultadoSolicitacao().contains("SEM_RESTRICAO")) {
				return true;
			}
		} catch (FeignException e) {
		}
		return false;
	}

	private String processa(SolicitaAnaliseRequest request, boolean status) {
		NovaProposta proposta = buscarProposta(request.getIdProposta());
		proposta.adicionaTransacao(request, status);
		verificadorCartao.setRequest(request);
		repository.save(proposta);
		if (proposta.processadaComSucesso()) {
			eventosNovaPropostaSucesso.forEach(evento -> evento.processa(proposta));
		}
		return proposta.toString();
	}

	public NovaProposta buscarProposta(Long propostaId) {
		return repository.findById(propostaId).orElseThrow(() -> new DatabaseException("id não localizado."));
	}

}

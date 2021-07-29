package com.zupacademy.proposta.novatransacao;

import java.net.URI;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zupacademy.proposta.analiseproposta.RetornoAnaliseRequest;
import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseFeign;
import com.zupacademy.proposta.analiseproposta.SolicitaAnaliseRequest;
import com.zupacademy.proposta.cartao.NovoCartaoSchedule;
import com.zupacademy.proposta.exceptions.DatabaseException;
import com.zupacademy.proposta.novaproposta.NovaProposta;
import com.zupacademy.proposta.novaproposta.NovaPropostaRepository;
import com.zupacademy.proposta.novaproposta.NovaPropostaStatus;

import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping(value = "/transacoes/")
public class NovaTransacaoController {

	@Autowired
	private NovaPropostaRepository repository;

	@Autowired
	private SolicitaAnaliseFeign solicitacaoAnaliseFeign;

	private Set<NovaTransacaoEvento> eventosNovaPropostaSucesso;

	@Autowired
	public NovaTransacaoController(Set<NovaTransacaoEvento> eventosNovaPropostaSucesso) {
		super();
		this.eventosNovaPropostaSucesso = eventosNovaPropostaSucesso;
	}

	@Autowired
	private NovoCartaoSchedule verificadorCartao;

	@Autowired
	private Tracer tracer;

	@Transactional
	@PostMapping(value = "/analise")
	public ResponseEntity<SolicitaAnaliseRequest> enviarPropostaParaAnalise(
			@RequestBody @Valid SolicitaAnaliseRequest request) {
		NovaProposta proposta = buscarProposta(request.getIdProposta());
		try {
			
			Span activeSpan = tracer.activeSpan();
			activeSpan.setTag("user.email", "henio.junior@zup.com.br");
			activeSpan.log("log");
			activeSpan.setBaggageItem("user.email", "henio.junior@zup.com.br");
			
			RetornoAnaliseRequest analise = solicitacaoAnaliseFeign
					.enviarParaAnalise(new SolicitaAnaliseRequest(proposta));
			proposta.setStatus(NovaPropostaStatus.ELEGIVEL);
			processa(request);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proposta.getId())
					.toUri();
			return ResponseEntity.created(uri).body(request);

		} catch (FeignException e) {
			proposta.setStatus(NovaPropostaStatus.NAO_ELEGIVEL);
		}
		return ResponseEntity.status(422).build();
	}

	private String processa(SolicitaAnaliseRequest request) {
		NovaProposta proposta = buscarProposta(request.getIdProposta());
		proposta.adicionaTransacao(request);
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

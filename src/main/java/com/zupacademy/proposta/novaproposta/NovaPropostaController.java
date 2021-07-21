package com.zupacademy.proposta.novaproposta;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zupacademy.proposta.exceptions.DatabaseException;
import com.zupacademy.proposta.solicitacaoanalise.RetornoAnaliseRequest;
import com.zupacademy.proposta.solicitacaoanalise.SolicitacaoAnaliseFeign;
import com.zupacademy.proposta.solicitacaoanalise.SolicitacaoAnaliseRequest;

import feign.FeignException;

@RestController
@RequestMapping(value = "/propostas")
public class NovaPropostaController {

	@Autowired
	NovaPropostaRepository repository;

	@Autowired
	SolicitacaoAnaliseFeign solicitacaoAnaliseFeign;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criarProposta(@RequestBody @Valid NovaPropostaRequest request) {

		Optional<NovaProposta> obj = repository.findByDocumento(request.getDocumento());

		if (obj.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("Já existe uma proposta para o documento: " + request.getDocumento());
		}
		NovaProposta novaProposta = request.toModel();
		repository.save(novaProposta);
		NovaPropostaResponse response = new NovaPropostaResponse(novaProposta);

		return ResponseEntity.status(HttpStatus.CREATED).body("http://localhost:8080/propostas/" + response.getId());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		NovaProposta novaProposta = repository.findById(id).get();
		NovaPropostaResponse response = new NovaPropostaResponse(novaProposta);
		return ResponseEntity.ok().body(response.toString());
	}

	@PostMapping(value = "/analise")
	ResponseEntity<?> solicitarAnalise(@RequestBody @Valid SolicitacaoAnaliseRequest analiseRequest) {
		Optional<NovaProposta> obj = repository.findById(analiseRequest.getIdProposta());
		NovaProposta novaProposta = obj.orElseThrow(() -> new DatabaseException("id não localizado."));
				 
		if (analisaStatus(analiseRequest)) {
			novaProposta.setStatus(StatusNovaProposta.ELEGIVEL);
		} else {
			novaProposta.setStatus(StatusNovaProposta.NAO_ELEGIVEL);
		}
		repository.save(novaProposta);
		return ResponseEntity.ok().body(new RetornoAnaliseRequest(novaProposta));
	}

	public boolean analisaStatus(SolicitacaoAnaliseRequest analiseRequest) {
		try {
			NovaProposta novaProposta = repository.findById(analiseRequest.getIdProposta()).get();
			RetornoAnaliseRequest analise = solicitacaoAnaliseFeign
					.enviarParaAnalise(new SolicitacaoAnaliseRequest(novaProposta));
			if (analise.getResultadoSolicitacao().contains("SEM_RESTRICAO")) {
				return true;
			}
		} catch (FeignException e) {

		}
		return false;
	}
}

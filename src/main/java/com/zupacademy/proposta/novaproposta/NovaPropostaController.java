package com.zupacademy.proposta.novaproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/propostas")
public class NovaPropostaController {
	
	@PersistenceContext
	EntityManager manager;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<NovaPropostaRequest> criarProposta(@RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriBuilder) {
		
		NovaProposta novaProposta = request.toModel();
		manager.persist(novaProposta);
				
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaProposta.getId()).toUri();
		return ResponseEntity.created(uri).body(new NovaPropostaRequest(novaProposta));
				
	}
}

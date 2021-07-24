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

@RestController
@RequestMapping(value = "/propostas")
public class NovaPropostaController {

	@Autowired
	NovaPropostaRepository repository;

	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarProposta(@RequestBody @Valid NovaPropostaRequest request) {

		Optional<NovaProposta> existeProposta = repository.findByDocumento(request.getDocumento());

		if (existeProposta.isPresent()) {
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
}

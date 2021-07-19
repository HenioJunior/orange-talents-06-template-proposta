package com.zupacademy.proposta.novaproposta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

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
	
	@PersistenceContext
	EntityManager manager;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<String> criarProposta(@RequestBody @Valid NovaPropostaRequest request) {
		
		NovaProposta novaProposta = request.toModel();
		manager.persist(novaProposta);
		NovaPropostaResponse response = new NovaPropostaResponse(novaProposta);

		return ResponseEntity.status(HttpStatus.CREATED)
		        .body("http://localhost:8080/propostas/" + response.getId());
	}
	
	@GetMapping(value = "/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        NovaProposta novaProposta= manager.find(NovaProposta.class, id);
        NovaPropostaResponse response = new NovaPropostaResponse(novaProposta);
        return ResponseEntity.ok().body(response.toString());
    }
}

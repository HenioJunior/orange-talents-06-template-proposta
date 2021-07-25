package com.zupacademy.proposta.novocartao;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zupacademy.proposta.exceptions.DatabaseException;


@RestController
@RequestMapping(value = "/cartoes")
public class NovoCartaoController {
	
	@Autowired
	private NovoCartaoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarBiometria(@RequestBody @Valid NovaBiometriaRequest request) {
		NovoCartao atualizaCartao = repository.findByIdCartao(request.getIdCartao()).orElseThrow(() -> new DatabaseException("id não localizado."));
		request.ToModel(atualizaCartao);
						
		repository.save(atualizaCartao);
		return ResponseEntity.status(HttpStatus.CREATED).body(request);
		
	}

}
